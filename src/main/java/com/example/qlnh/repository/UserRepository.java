package com.example.qlnh.repository;

import com.example.qlnh.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmail(String email);

  @Query("UPDATE User u SET u.failedAttempt = ?1 WHERE u.email = ?2")
  @Modifying
  public void updateFailedAttempts(int failAttempts, String email);
}
