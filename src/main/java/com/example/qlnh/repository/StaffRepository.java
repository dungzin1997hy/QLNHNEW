package com.example.qlnh.repository;

import com.example.qlnh.entities.Staff;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
  Staff findStaffById(int id);

  Staff findStaffByPhoneNumber(String phoneNumber);

  Staff findStaffByIdCard(String idCard);

  List<Staff> findStaffByPhoneNumberContains(String phoneNumber, Sort sort);

  Staff findStaffByPhoneNumberAndIdNot(String phoneNumber, int id);

  Staff findStaffByIdCardAndIdNot(String idCard, int id);
}
