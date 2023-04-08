package com.example.qlnh.repository;

import com.example.qlnh.entities.Table;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
  Table findTableById(int id);

  Table findTableByName(String name);

  Table findTableByNameAndIdNot(String name, int id);

  List<Table> findTableByNameContains(String name);
}
