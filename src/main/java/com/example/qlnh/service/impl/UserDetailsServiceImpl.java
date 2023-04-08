package com.example.qlnh.service.impl;

import com.example.qlnh.entities.User;
import com.example.qlnh.exception.WrongEmailOrPasswordException;
import com.example.qlnh.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository userRepository;
  private static final long LIMIT_ACCOUNT_LOCK = 60 * 60 * 24 * 30 * 2;

  @Override
  public UserDetails loadUserByUsername(String username) {
    User user = userRepository.findByEmail(username);
    if (user == null) {
      throw new WrongEmailOrPasswordException();
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole());
    authorities.add(authority);

    boolean accountNonExpired =
        user.getLastLoginAt() + LIMIT_ACCOUNT_LOCK > System.currentTimeMillis() / 1000;

    return new org.springframework.security.core.userdetails.User(
        user.getEmail(),
        user.getPassword(),
        true,
        accountNonExpired,
        true,
        user.getAccountNonLocked(),
        authorities);
  }
}
