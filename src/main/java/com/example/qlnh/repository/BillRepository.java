package com.example.qlnh.repository;

import com.example.qlnh.entities.Bill;
import com.example.qlnh.entities.Staff;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
  Page<Bill> findBillByCreateAtGreaterThanEqualAndCreateAtLessThanEqual(
      OffsetDateTime fromDate, OffsetDateTime toDate, Pageable pageable);

  List<Bill> findBillsByStaff(Staff staff);
}
