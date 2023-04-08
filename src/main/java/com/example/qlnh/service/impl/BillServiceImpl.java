package com.example.qlnh.service.impl;

import com.example.qlnh.common.PayStatus;
import com.example.qlnh.common.Status;
import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BillCreateDto;
import com.example.qlnh.dto.response.BillResponseDto;
import com.example.qlnh.dto.response.CustomerResponseDto;
import com.example.qlnh.dto.response.DishResponseDto;
import com.example.qlnh.dto.response.TableResponseDto;
import com.example.qlnh.dto.response.UsedDishResponseDto;
import com.example.qlnh.entities.Bill;
import com.example.qlnh.entities.Customer;
import com.example.qlnh.entities.Staff;
import com.example.qlnh.entities.Table;
import com.example.qlnh.entities.UsedDish;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.BillRepository;
import com.example.qlnh.repository.CustomerRepository;
import com.example.qlnh.repository.StaffRepository;
import com.example.qlnh.repository.TableRepository;
import com.example.qlnh.repository.UsedDishRepository;
import com.example.qlnh.service.BillService;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
  private final BillRepository billRepository;
  private final UsedDishRepository usedDishRepository;
  private final ModelMapper modelMapper;
  private final TableRepository tableRepository;
  private final CustomerRepository customerRepository;
  private final StaffRepository staffRepository;

  @Override
  @Transactional
  public CommonRes<String> create(BillCreateDto billDto) {
    Table table =
        tableRepository
            .findById(billDto.getTableId())
            .orElseThrow(() -> new NotFoundException("tableId"));
    table.setStatus(Status.FREE.getValue());
    Customer customer = null;
    if (billDto.getCustomerId() != null) {
      customer =
          customerRepository
              .findById(billDto.getCustomerId())
              .orElseThrow(() -> new NotFoundException("customerId"));
    }
    Staff staff =
        staffRepository
            .findById(billDto.getStaffId())
            .orElseThrow(() -> new NotFoundException("staffId"));

    Bill bill =
        Bill.builder()
            .createAt(OffsetDateTime.now())
            .payType(billDto.getPayType().ordinal())
            .total(billDto.getTotal())
            .table(table)
            .staff(staff)
            .customer(customer)
            .build();
    bill = billRepository.save(bill);
    List<UsedDish> usedDishes = new ArrayList<>();
    Bill finalBill = bill;
    billDto
        .getUsedDishIds()
        .forEach(
            id -> {
              UsedDish usedDish = usedDishRepository.findUsedDishById(id);
              usedDish.setStatus(PayStatus.PAID.getValue());
              usedDish.setBill(finalBill);

              usedDishes.add(usedDish);
            });
    usedDishRepository.saveAll(usedDishes);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert bill success!");
    return response;
  }

  @Override
  public CommonRes<List<BillResponseDto>> readAll(int pageIndex, int pageSize) {
    List<BillResponseDto> billResponseDtos = new ArrayList<>();
    Page<Bill> billPage = billRepository.findAll(PageRequest.of(pageIndex - 1, pageSize));
    return getCommonResResponseEntity(billResponseDtos, billPage);
  }

  @Override
  public CommonRes<List<BillResponseDto>> readByCreateAt(
      int pageIndex, int pageSize, String dateTimeFrom, String dateTimeTo) {
    DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
    List<BillResponseDto> billResponseDtos = new ArrayList<>();

    Page<Bill> billPage =
        billRepository.findBillByCreateAtGreaterThanEqualAndCreateAtLessThanEqual(
            OffsetDateTime.parse(dateTimeFrom, dtf),
            OffsetDateTime.parse(dateTimeTo, dtf),
            PageRequest.of(pageIndex - 1, pageSize));

    return getCommonResResponseEntity(billResponseDtos, billPage);
  }

  private CommonRes<List<BillResponseDto>> getCommonResResponseEntity(
      List<BillResponseDto> billResponseDtos, Page<Bill> billPage) {
    List<Bill> bills = billPage.getContent();
    bills.forEach(
        bill -> {
          TableResponseDto tableResponseDto =
              TableResponseDto.builder()
                  .id(bill.getTable().getId())
                  .name(bill.getTable().getName())
                  .build();
          CustomerResponseDto customerResponseDto = null;
          if (Objects.nonNull(bill.getCustomer())) {
            customerResponseDto = modelMapper.map(bill.getCustomer(), CustomerResponseDto.class);
          }
          List<UsedDishResponseDto> usedDishResponseDtos =
              bill.getUsedDishes().stream()
                  .map(
                      usedDish -> {
                        DishResponseDto dishResponseDto =
                            modelMapper.map(usedDish.getDish(), DishResponseDto.class);
                        return UsedDishResponseDto.builder()
                            .dish(dishResponseDto)
                            .time(usedDish.getTime())
                            .amount(usedDish.getAmount())
                            .status(usedDish.getStatus())
                            .build();
                      })
                  .toList();

          BillResponseDto billResponseDto =
              BillResponseDto.builder()
                  .createAt(bill.getCreateAt())
                  .id(bill.getId())
                  .payType(bill.getPayType())
                  .total(bill.getTotal())
                  .usedDishes(usedDishResponseDtos)
                  .table(tableResponseDto)
                  .customer(customerResponseDto)
                  .build();
          billResponseDtos.add(billResponseDto);
        });
    CommonRes<List<BillResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(billResponseDtos);
    return response;
  }
}
