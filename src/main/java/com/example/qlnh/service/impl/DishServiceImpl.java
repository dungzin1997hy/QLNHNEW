package com.example.qlnh.service.impl;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.DishCreateDto;
import com.example.qlnh.dto.request.update.DishUpdateDto;
import com.example.qlnh.dto.response.DishResponseDto;
import com.example.qlnh.entities.Dish;
import com.example.qlnh.exception.DuplicateException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.DishRepository;
import com.example.qlnh.repository.UsedDishRepository;
import com.example.qlnh.service.DishService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DishServiceImpl implements DishService {
  private final DishRepository dishRepository;
  private final UsedDishRepository usedDishRepository;

  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(DishCreateDto dishCreateDto) {
    Dish dish = dishRepository.findDishByName(dishCreateDto.getName());
    if (dish != null) {
      throw new DuplicateException("dishName");
    }
    Dish dishInsert = modelMapper.map(dishCreateDto, Dish.class);
    dishRepository.save(dishInsert);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert new dish success!");
    return response;
  }

  @Override
  public CommonRes<List<DishResponseDto>> readAll() {
    CommonRes<List<DishResponseDto>> response = new CommonRes<>();
    List<Dish> dishes = dishRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    List<DishResponseDto> dishResponseDtos =
        dishes.stream().map(dish -> modelMapper.map(dish, DishResponseDto.class)).toList();
    response.setResult("OK");
    response.setData(dishResponseDtos);
    return response;
  }

  @Override
  public CommonRes<List<DishResponseDto>> readByName(String name) {
    CommonRes<List<DishResponseDto>> response = new CommonRes<>();
    List<Dish> dishes = dishRepository.findDishByNameContains(name);
    List<DishResponseDto> dishResponseDtos =
        dishes.stream().map(dish -> modelMapper.map(dish, DishResponseDto.class)).toList();
    response.setResult("OK");
    response.setData(dishResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> update(DishUpdateDto dishUpdateDto) {
    CommonRes<String> response = new CommonRes<>();
    Dish dish =
        dishRepository
            .findById(dishUpdateDto.getId())
            .orElseThrow(() -> new NotFoundException("dishId"));

    Optional<Dish> dishOptional =
        dishRepository.findDishByNameAndIdNot(dishUpdateDto.getName(), dishUpdateDto.getId());

    dishOptional.ifPresent(
        object -> {
          throw new DuplicateException("dishName");
        });

    dish = modelMapper.map(dishUpdateDto, Dish.class);
    dishRepository.save(dish);
    response.setResult("OK");
    response.setData("Update dish success!!");
    return response;
  }

  @Override
  public CommonRes<String> delete(int id) {

    Dish dish = dishRepository.findById(id).orElseThrow(() -> new NotFoundException("dishId"));
    dishRepository.delete(dish);
    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Delete dish success!!");
    return response;
  }
}
