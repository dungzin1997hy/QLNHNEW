package com.example.qlnh.service.impl;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.StaffCreateDto;
import com.example.qlnh.dto.request.update.StaffUpdateDto;
import com.example.qlnh.dto.response.StaffResponseDto;
import com.example.qlnh.entities.Bill;
import com.example.qlnh.entities.Staff;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.BillRepository;
import com.example.qlnh.repository.StaffRepository;
import com.example.qlnh.service.StaffService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
  private final StaffRepository staffRepository;
  private final BillRepository billRepository;
  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(StaffCreateDto staffCreateDto) {
    Staff staff = staffRepository.findStaffByPhoneNumber(staffCreateDto.getPhoneNumber());
    if (staff != null) {
      throw new DuplicateException("phone number");
    }

    staff = staffRepository.findStaffByIdCard(staffCreateDto.getIdCard());
    if (staff != null) {
      throw new DuplicateException("id card");
    }

    Staff staffInsert =
        Staff.builder()
            .name(staffCreateDto.getName())
            .email(staffCreateDto.getEmail())
            .idCard(staffCreateDto.getIdCard())
            .phoneNumber(staffCreateDto.getPhoneNumber())
            .build();
    staffRepository.save(staffInsert);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert new staff success!");
    return response;
  }

  @Override
  public CommonRes<List<StaffResponseDto>> readAll() {
    List<Staff> staffs = staffRepository.findAll(Sort.by(Direction.ASC, "name"));
    List<StaffResponseDto> staffResponseDtos =
        staffs.stream().map(staff -> modelMapper.map(staff, StaffResponseDto.class)).toList();

    CommonRes<List<StaffResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(staffResponseDtos);
    return response;
  }

  @Override
  public CommonRes<List<StaffResponseDto>> readByPhoneNumber(String phoneNumber) {
    List<Staff> staffs =
        staffRepository.findStaffByPhoneNumberContains(phoneNumber, Sort.by(Direction.ASC, "name"));
    List<StaffResponseDto> staffResponseDtos =
        staffs.stream().map(staff -> modelMapper.map(staff, StaffResponseDto.class)).toList();
    CommonRes<List<StaffResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(staffResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> update(StaffUpdateDto staffUpdateDto) {
    staffRepository
        .findById(staffUpdateDto.getId())
        .orElseThrow(() -> new NotFoundException("staffId"));

    Staff staff =
        staffRepository.findStaffByPhoneNumberAndIdNot(
            staffUpdateDto.getPhoneNumber(), staffUpdateDto.getId());

    if (staff != null) {
      throw new DuplicateException("phoneNumber");
    }

    staff =
        staffRepository.findStaffByIdCardAndIdNot(
            staffUpdateDto.getIdCard(), staffUpdateDto.getId());

    if (staff != null) {
      throw new DuplicateException("idCard");
    }

    Staff staffUpdate = modelMapper.map(staffUpdateDto, Staff.class);
    staffRepository.save(staffUpdate);
    return CommonRes.<String>builder().result("OK").data("Update staff success").build();
  }

  @Override
  @Transactional
  public CommonRes<String> delete(int id) {
    Staff staff = staffRepository.findById(id).orElseThrow(() -> new NotFoundException("staffId"));

    List<Bill> bills = billRepository.findBillsByStaff(staff);
    bills.forEach(bill -> bill.setStaff(null));
    billRepository.saveAll(bills);

    staffRepository.delete(staff);
    return CommonRes.<String>builder().result("OK").data("Delete staff success!!").build();
  }
}
