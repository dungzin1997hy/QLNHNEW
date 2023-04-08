package com.example.qlnh.service.impl;

import com.example.qlnh.common.Status;
import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.UsedDishCreateDto;
import com.example.qlnh.dto.response.UsedDishResponseDto;
import com.example.qlnh.entities.Dish;
import com.example.qlnh.entities.Table;
import com.example.qlnh.entities.UsedDish;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.exception.TableIsNotUsed;
import com.example.qlnh.repository.DishRepository;
import com.example.qlnh.repository.TableRepository;
import com.example.qlnh.repository.UsedDishRepository;
import com.example.qlnh.service.UsedDishService;
import java.time.OffsetDateTime;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class UsedDishServiceImpl implements UsedDishService {
  private final UsedDishRepository usedDishRepository;
  private final DishRepository dishRepository;
  private final TableRepository tableRepository;
  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(UsedDishCreateDto usedDishCreateDto) {
    Table table =
        tableRepository
            .findById(usedDishCreateDto.getTableId())
            .orElseThrow(() -> new NotFoundException("tableId"));

    if (Status.FREE.getValue().equals(table.getStatus())) {
      throw new TableIsNotUsed();
    }
    Dish dish =
        dishRepository
            .findById(usedDishCreateDto.getDishId())
            .orElseThrow(() -> new NotFoundException("dishId"));

    UsedDish usedDish =
        UsedDish.builder()
            .amount(usedDishCreateDto.getAmount())
            .dish(dish)
            .table(table)
            .time(OffsetDateTime.now())
            .status("not paid")
            .build();
    log.info(usedDish);
    usedDishRepository.save(usedDish);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert used dish success!");
    return response;
  }

  @Override
  public CommonRes<List<UsedDishResponseDto>> readByTable(int tableId) {
    CommonRes<List<UsedDishResponseDto>> response = new CommonRes<>();
    Table table = Table.builder().id(tableId).build();
    List<UsedDish> usedDishes =
        usedDishRepository.findAllByTableAndStatusOrderByTimeAsc(table, "not paid");
    List<UsedDishResponseDto> usedDishResponseDtos =
        usedDishes.stream()
            .map(usedDish -> modelMapper.map(usedDish, UsedDishResponseDto.class))
            .toList();
    response.setResult("OK");
    response.setData(usedDishResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> delete(int id) {
    CommonRes<String> response = new CommonRes<>();
    var usedDish = UsedDish.builder().id(id).build();
    usedDishRepository.delete(usedDish);
    response.setResult("OK");
    response.setData("Delete usedDish success!!");
    return response;
  }
}
