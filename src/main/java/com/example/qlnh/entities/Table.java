package com.example.qlnh.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@javax.persistence.Table(name = "[table]")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Table {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Length(min = 1, max = 255)
  private String name;

  @NotNull
  @Pattern(regexp = "using|free", message = "status must be used or free")
  private String status;

  @OneToMany(mappedBy = "table", cascade = CascadeType.REMOVE)
  @JsonManagedReference
  private List<UsedDish> usedDishes;

  @OneToMany(mappedBy = "table", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<Bill> bills;
}
