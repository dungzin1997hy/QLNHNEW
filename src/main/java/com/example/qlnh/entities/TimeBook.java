package com.example.qlnh.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "time_book")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeBook {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "time_from")
  private String timeFrom;

  @Column(name = "time_to")
  private String timeTo;
}
