package com.example.qlnh.repository;

import com.example.qlnh.entities.Dish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Integer> {
  List<Dish> findDishByNameContains(String name);

  Dish findDishByName(String name);

  Optional<Dish> findDishByNameAndIdNot(String name, int id);
}
