package com.example.qlnh.repository;

import com.example.qlnh.entities.Customer;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
  Customer findCustomerById(int id);

  Customer findCustomerByPhoneNumber(String phoneNumber);

  List<Customer> findCustomerByPhoneNumberContains(String phoneNumber, Sort sort);
}
