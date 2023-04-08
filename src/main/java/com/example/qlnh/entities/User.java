package com.example.qlnh.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String email;

  private String password;

  @Column(name = "last_login_at")
  private Long lastLoginAt;

  @Column(name = "account_non_locked")
  private Boolean accountNonLocked;

  @Column(name = "failed_attempt")
  private Integer failedAttempt;

  @Column(name = "lock_time")
  private Long lockTime;

  private String role;

  private String token;
}
