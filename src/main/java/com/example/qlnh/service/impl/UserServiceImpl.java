package com.example.qlnh.service.impl;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.UserLoginDto;
import com.example.qlnh.dto.request.create.UserCreateDto;
import com.example.qlnh.dto.request.update.UserForgotPasswordDto;
import com.example.qlnh.dto.request.update.UserUpdateDto;
import com.example.qlnh.entities.User;
import com.example.qlnh.exception.AccountLockException;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.InvalidTokenException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.exception.SendMailException;
import com.example.qlnh.exception.WrongEmailOrPasswordException;
import com.example.qlnh.exception.WrongPasswordException;
import com.example.qlnh.repository.UserRepository;
import com.example.qlnh.service.UserService;
import java.util.Objects;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserDetailsServiceImpl userDetailsService;
  private final AuthenticationManager authenticationManager;
  private final JavaMailSender mailSender;

  public static final int MAX_FAILED_ATTEMPTS = 3;
  private static final long LOCK_TIME_DURATION = 60 * 10 * 1000;

  @Override
  public CommonRes<String> create(UserCreateDto userCreateDto) {
    User user = userRepository.findByEmail(userCreateDto.getEmail());
    if (user != null) {
      throw new DuplicateException("userName");
    }

    var userInsert =
        User.builder()
            .email(userCreateDto.getEmail())
            .password(passwordEncoder.encode(userCreateDto.getPassword()))
            .accountNonLocked(true)
            .lastLoginAt(System.currentTimeMillis() / 1000)
            .role("ADMIN")
            .build();
    userRepository.save(userInsert);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Register new account success");
    return response;
  }

  @Override
  @Transactional(dontRollbackOn = {AccountLockException.class, WrongEmailOrPasswordException.class})
  public CommonRes<String> login(UserLoginDto userLoginDto) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginDto.getEmail());
    User user = userRepository.findByEmail(userLoginDto.getEmail());

    if (!user.getAccountNonLocked()) {
      if (unlockWhenTimeExpired(user)) {
        throw new AccountLockException(
            "Your account has been unlocked. Please try to login again.");
      }
    }

    if (passwordEncoder.matches(userLoginDto.getPassword(), userDetails.getPassword())) {
      UsernamePasswordAuthenticationToken token =
          new UsernamePasswordAuthenticationToken(
              userDetails, userLoginDto.getPassword(), userDetails.getAuthorities());
      authenticationManager.authenticate(token);

      if (token.isAuthenticated()) {
        if (user.getFailedAttempt() > 0) {
          user.setFailedAttempt(0);
        }
        user.setLastLoginAt(System.currentTimeMillis() / 1000);
        userRepository.save(user);

        SecurityContextHolder.getContext().setAuthentication(token);
        CommonRes<String> response = new CommonRes<>();
        response.setResult("OK");
        response.setData("Login success");
        return response;
      }
    } else {
      if (user.getFailedAttempt() < MAX_FAILED_ATTEMPTS - 1) {
        increaseFailedAttempts(user);
        throw new WrongEmailOrPasswordException();
      } else {
        lock(user);
        throw new AccountLockException(
            "Your account has been locked due to 3 failed attempts."
                + " It will be unlocked after 10 minutes.");
      }
    }
    return null;
  }

  @Override
  public CommonRes<String> update(UserUpdateDto userUpdateDto) {
    User user = userRepository.findByEmail(userUpdateDto.getEmail());

    if (Objects.isNull(user)) {
      throw new NotFoundException("email");
    }

    if (passwordEncoder.matches(userUpdateDto.getOldPassword(), user.getPassword())) {
      user.setPassword(passwordEncoder.encode(userUpdateDto.getNewPassword()));
      user.setFailedAttempt(0);
      user.setAccountNonLocked(true);
      user.setLockTime(null);
      user.setLastLoginAt(System.currentTimeMillis() / 1000);
      userRepository.save(user);

      CommonRes<String> response = new CommonRes<>();
      response.setResult("OK");
      response.setData("Change password success!!");
      return response;
    } else {
      throw new WrongPasswordException();
    }
  }

  @Override
  @Transactional
  public CommonRes<String> forgotPassword(String email) {
    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      throw new NotFoundException("email");
    }

    String token = RandomStringUtils.randomAlphabetic(20);
    sendEmail(email, token);
    user.setToken(token);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Send email success, please check!!");
    return response;
  }

  @Override
  public CommonRes<String> processForgotPassword(UserForgotPasswordDto userForgotPasswordDto) {
    User user = userRepository.findByEmail(userForgotPasswordDto.getEmail());
    if (Objects.isNull(user)) {
      throw new NotFoundException("email");
    }

    if (user.getToken().equals(userForgotPasswordDto.getToken())) {
      user.setToken(null);
      user.setPassword(userForgotPasswordDto.getNewPassword());
      user.setLockTime(null);
      user.setAccountNonLocked(true);
      user.setFailedAttempt(0);
      user.setToken(null);
      userRepository.save(user);
      CommonRes<String> response = new CommonRes<>();
      response.setResult("OK");
      response.setData("Change new password success");
      return response;
    } else {
      throw new InvalidTokenException();
    }
  }

  private void increaseFailedAttempts(User user) {
    int newFailAttempts = user.getFailedAttempt() + 1;
    userRepository.updateFailedAttempts(newFailAttempts, user.getEmail());
  }

  private void lock(User user) {
    user.setAccountNonLocked(false);
    user.setLockTime(System.currentTimeMillis() / 1000);
    userRepository.save(user);
  }

  private boolean unlockWhenTimeExpired(User user) {
    long lockTimeInMillis = user.getLockTime();
    long currentTimeInMillis = System.currentTimeMillis() / 1000;

    if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
      user.setAccountNonLocked(true);
      user.setLockTime(null);
      user.setFailedAttempt(0);

      userRepository.save(user);

      return true;
    }

    return false;
  }

  private void sendEmail(String recipientEmail, String token) {
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);

      helper.setFrom("dungzin1997hy@gmail.com");
      helper.setTo(recipientEmail);

      String subject = "Here's the link to reset your password";

      String content =
          "<p>Hello,</p>"
              + "<p>You have requested to reset your password.</p>"
              + "<p>Token:</p>"
              + "<p>"
              + token
              + "</p>";

      helper.setSubject(subject);

      helper.setText(content, true);

      mailSender.send(message);
    } catch (Exception e) {
      throw new SendMailException();
    }
  }
}
