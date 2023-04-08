package com.example.qlnh.service.impl;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.CustomerCreateDto;
import com.example.qlnh.dto.request.update.CustomerUpdateDto;
import com.example.qlnh.dto.response.CustomerResponseDto;
import com.example.qlnh.entities.Customer;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.CustomerRepository;
import com.example.qlnh.service.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(CustomerCreateDto customerCreateDto) {
    CommonRes<String> response = new CommonRes<>();
    Customer customer =
        customerRepository.findCustomerByPhoneNumber(customerCreateDto.getPhoneNumber());
    if (customer != null) {
      throw new DuplicateException("phone number");
    }
    Customer customerInsert = modelMapper.map(customerCreateDto, Customer.class);
    customerRepository.save(customerInsert);

    response.setResult("OK");
    response.setData("Insert new customer success!");
    return response;
  }

  @Override
  public CommonRes<List<CustomerResponseDto>> readAll() {
    List<Customer> customers = customerRepository.findAll(Sort.by(Direction.ASC, "name"));
    List<CustomerResponseDto> customerResponseDtos =
        customers.stream()
            .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
            .toList();

    CommonRes<List<CustomerResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(customerResponseDtos);
    return response;
  }

  @Override
  public CommonRes<List<CustomerResponseDto>> readByPhoneNumber(String phoneNumber) {
    List<Customer> customers =
        customerRepository.findCustomerByPhoneNumberContains(
            phoneNumber, Sort.by(Direction.ASC, "name"));
    List<CustomerResponseDto> customerResponseDtos =
        customers.stream()
            .map(customer -> modelMapper.map(customer, CustomerResponseDto.class))
            .toList();

    CommonRes<List<CustomerResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(customerResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> update(CustomerUpdateDto customerUpdateDto) {
    customerRepository
        .findById(customerUpdateDto.getId())
        .orElseThrow(() -> new NotFoundException("customerId"));

    Customer customerUpdate = modelMapper.map(customerUpdateDto, Customer.class);
    customerRepository.save(customerUpdate);
    return CommonRes.<String>builder().result("OK").data("Update customer success").build();
  }

  @Override
  public CommonRes<String> delete(int id) {
    customerRepository.findById(id).orElseThrow(() -> new NotFoundException("customerId"));
    var customer = Customer.builder().id(id).build();
    //    List<UsedDish> usedDishes = usedDishRepository.findAllByDish(dish);
    //    usedDishes.forEach(usedDish -> usedDish.setDish(null));
    //    usedDishRepository.saveAll(usedDishes);
    customerRepository.delete(customer);
    return CommonRes.<String>builder().result("OK").data("Delete customer success!!").build();
  }
}
