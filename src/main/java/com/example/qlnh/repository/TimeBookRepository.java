package com.example.qlnh.repository;

import com.example.qlnh.entities.TimeBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeBookRepository extends JpaRepository<TimeBook, Integer> {}
