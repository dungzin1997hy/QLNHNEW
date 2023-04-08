package com.example.qlnh.repository;

import com.example.qlnh.entities.Booking;
import com.example.qlnh.entities.Customer;
import com.example.qlnh.entities.Table;
import com.example.qlnh.entities.TimeBook;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
  Optional<Booking> findBookingByTableAndTimeBook(Table table, TimeBook timeBook);

  Optional<Booking> findBookingByIdAndStatus(int id, String status);

  Optional<Booking> findBookingByCustomerAndStatus(Customer customer, String status);

  List<Booking> findBookingByTimeBook(TimeBook timeBook);
}
