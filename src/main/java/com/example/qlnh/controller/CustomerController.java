package com.example.qlnh.controller;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.CustomerCreateDto;
import com.example.qlnh.dto.request.update.CustomerUpdateDto;
import com.example.qlnh.dto.response.CustomerResponseDto;
import com.example.qlnh.service.CustomerService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
  private final CustomerService customerService;

  @PostMapping("/create")
  public CommonRes<String> create(@Valid @RequestBody CustomerCreateDto customerCreateDto) {
    return customerService.create(customerCreateDto);
  }

  @GetMapping
  public CommonRes<List<CustomerResponseDto>> readAll() {
    return customerService.readAll();
  }

  @GetMapping("/search")
  public CommonRes<List<CustomerResponseDto>> readByPhoneNumber(@RequestParam String phoneNumber) {
    return customerService.readByPhoneNumber(phoneNumber);
  }

  @PutMapping("/update")
  public CommonRes<String> update(@Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
    return customerService.update(customerUpdateDto);
  }

  @DeleteMapping("/delete")
  public CommonRes<String> delete(@RequestParam Integer id) {
    return customerService.delete(id);
  }
}
