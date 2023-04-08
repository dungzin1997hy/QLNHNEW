package com.example.qlnh.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.OffsetDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@javax.persistence.Table(name = "used_dish")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsedDish {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int amount;
  private OffsetDateTime time;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "table_id", referencedColumnName = "id")
  private Table table;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "dish_id", referencedColumnName = "id")
  private Dish dish;

  private String status;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "bill_id")
  private Bill bill;
}
