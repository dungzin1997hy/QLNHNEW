package com.example.qlnh.service.impl;

import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.TimeBookCreateDto;
import com.example.qlnh.dto.request.update.TimebookUpdateDto;
import com.example.qlnh.dto.response.TimeBookResponseDto;
import com.example.qlnh.entities.Booking;
import com.example.qlnh.entities.TimeBook;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.BookingRepository;
import com.example.qlnh.repository.TimeBookRepository;
import com.example.qlnh.service.TimeBookService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeBookServiceImpl implements TimeBookService {
  private final TimeBookRepository timeBookRepository;
  private final BookingRepository bookingRepository;
  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(TimeBookCreateDto timeBookCreateDto) {
    TimeBook timeBook = modelMapper.map(timeBookCreateDto, TimeBook.class);
    timeBookRepository.save(timeBook);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert new time book success!");
    return response;
  }

  @Override
  public CommonRes<List<TimeBookResponseDto>> readAll() {
    List<TimeBook> staffs = timeBookRepository.findAll(Sort.by(Direction.ASC, "timeFrom"));
    List<TimeBookResponseDto> timeBookResponseDtos =
        staffs.stream()
            .map(timeBook -> modelMapper.map(timeBook, TimeBookResponseDto.class))
            .toList();

    CommonRes<List<TimeBookResponseDto>> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(timeBookResponseDtos);
    return response;
  }

  @Override
  public CommonRes<String> update(TimebookUpdateDto timebookUpdateDto) {
    timeBookRepository
        .findById(timebookUpdateDto.getId())
        .orElseThrow(() -> new NotFoundException("timeBookId"));

    TimeBook timeBook = modelMapper.map(timebookUpdateDto, TimeBook.class);
    timeBookRepository.save(timeBook);
    return CommonRes.<String>builder().result("OK").data("Update time book success").build();
  }

  @Override
  @Transactional
  public CommonRes<String> delete(int id) {
    timeBookRepository.findById(id).orElseThrow(() -> new NotFoundException("timeBookId"));
    var timeBook = TimeBook.builder().id(id).build();

    List<Booking> bookings = bookingRepository.findBookingByTimeBook(timeBook);
    bookings.forEach(booking -> booking.setTimeBook(null));

    bookingRepository.saveAll(bookings);

    timeBookRepository.delete(timeBook);
    return CommonRes.<String>builder().result("OK").data("Delete time book success!!").build();
  }
}
