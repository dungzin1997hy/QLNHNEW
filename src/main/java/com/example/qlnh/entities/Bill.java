package com.example.qlnh.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.OffsetDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bill {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private int total;
  private int payType;

  private OffsetDateTime createAt;

  @OneToMany(mappedBy = "bill", cascade = CascadeType.REMOVE)
  @JsonManagedReference
  private List<UsedDish> usedDishes;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "table_id")
  private com.example.qlnh.entities.Table table;

  @ManyToOne
  @JsonBackReference
  @JoinColumn(name = "customer_id")
  private Customer customer;

  @ManyToOne
  @JoinColumn(name = "staff_id")
  private Staff staff;
}
