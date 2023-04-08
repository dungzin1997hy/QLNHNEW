package com.example.qlnh.repository;

import com.example.qlnh.entities.Dish;
import com.example.qlnh.entities.Table;
import com.example.qlnh.entities.UsedDish;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedDishRepository extends JpaRepository<UsedDish, Integer> {
  List<UsedDish> findAllByTableAndStatusOrderByTimeAsc(Table table, String status);

  UsedDish findUsedDishById(int id);

  List<UsedDish> findAllByDish(Dish dish);

  List<UsedDish> findAllByTable(Table table);
}
