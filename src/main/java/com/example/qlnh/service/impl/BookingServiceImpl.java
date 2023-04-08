package com.example.qlnh.service.impl;

import static com.example.qlnh.common.BookingStatus.IN_PROGRESS;

import com.example.qlnh.common.BookingStatus;
import com.example.qlnh.common.Status;
import com.example.qlnh.dto.CommonRes;
import com.example.qlnh.dto.request.create.BookingCreateDto;
import com.example.qlnh.dto.request.update.BookingUpdateDto;
import com.example.qlnh.dto.response.BookingResponseDto;
import com.example.qlnh.dto.response.CustomerResponseDto;
import com.example.qlnh.dto.response.TableResponseDto;
import com.example.qlnh.dto.response.TimeBookResponseDto;
import com.example.qlnh.entities.Booking;
import com.example.qlnh.entities.Customer;
import com.example.qlnh.entities.Table;
import com.example.qlnh.entities.TimeBook;
import com.example.qlnh.exception.BookingExistedException;
import com.example.qlnh.exception.NotFoundException;
import com.example.qlnh.repository.BookingRepository;
import com.example.qlnh.repository.CustomerRepository;
import com.example.qlnh.repository.TableRepository;
import com.example.qlnh.repository.TimeBookRepository;
import com.example.qlnh.service.BookingService;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
  private final BookingRepository bookingRepository;
  private final TableRepository tableRepository;
  private final CustomerRepository customerRepository;
  private final TimeBookRepository timeBookRepository;

  private final ModelMapper modelMapper;

  @Override
  public CommonRes<String> create(BookingCreateDto bookingCreateDto) {
    Customer customer =
        customerRepository
            .findById(bookingCreateDto.getCustomerId())
            .orElseThrow(() -> new NotFoundException("customerId"));

    Table table =
        tableRepository
            .findById(bookingCreateDto.getTableId())
            .orElseThrow(() -> new NotFoundException("tableId"));

    TimeBook timeBook =
        timeBookRepository
            .findById(bookingCreateDto.getTimeBookId())
            .orElseThrow(() -> new NotFoundException("timeBookId"));

    Optional<Booking> bookingOptional =
        bookingRepository.findBookingByTableAndTimeBook(table, timeBook);
    bookingOptional.ifPresent(
        existedBooking -> {
          throw new BookingExistedException();
        });

    var booking =
        Booking.builder()
            .customer(customer)
            .timeBook(timeBook)
            .table(table)
            .status(IN_PROGRESS.getValue())
            .build();

    bookingRepository.save(booking);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Insert booking success!");
    return response;
  }

  @Override
  public CommonRes<String> update(BookingUpdateDto bookingUpdateDto) {
    Booking booking =
        bookingRepository
            .findById(bookingUpdateDto.getId())
            .orElseThrow(() -> new NotFoundException("bookingId"));

    Customer customer = Customer.builder().id(bookingUpdateDto.getCustomerId()).build();
    Table table = Table.builder().id(bookingUpdateDto.getTableId()).build();
    TimeBook timeBook = TimeBook.builder().id(bookingUpdateDto.getTimeBookId()).build();

    var bookingUpdate =
        Booking.builder()
            .id(booking.getId())
            .customer(customer)
            .table(table)
            .timeBook(timeBook)
            .build();

    bookingRepository.save(bookingUpdate);

    CommonRes<String> response = new CommonRes<>();
    response.setResult("OK");
    response.setData("Update booking success!");
    return response;
  }

  @Override
  public CommonRes<BookingResponseDto> readByCustomer(int customerId) {
    Customer customer =
        customerRepository
            .findById(customerId)
            .orElseThrow(() -> new NotFoundException("customerId"));

    Booking booking =
        bookingRepository
            .findBookingByCustomerAndStatus(customer, IN_PROGRESS.getValue())
            .orElseThrow(() -> new NotFoundException("booking"));

    CustomerResponseDto customerResponseDto =
        modelMapper.map(booking.getCustomer(), CustomerResponseDto.class);
    TableResponseDto tableResponseDto = modelMapper.map(booking.getTable(), TableResponseDto.class);
    TimeBookResponseDto timeBookResponseDto =
        modelMapper.map(booking.getTimeBook(), TimeBookResponseDto.class);

    BookingResponseDto bookingResponseDto =
        BookingResponseDto.builder()
            .id(booking.getId())
            .customer(customerResponseDto)
            .table(tableResponseDto)
            .timeBook(timeBookResponseDto)
            .status(booking.getStatus())
            .build();

    CommonRes<BookingResponseDto> response = new CommonRes<>();
    response.setResult("OK");
    response.setData(bookingResponseDto);
    return response;
  }

  @Override
  public CommonRes<String> delete(int id) {
    bookingRepository.findById(id).orElseThrow(() -> new NotFoundException("bookingId"));
    var booking = Booking.builder().id(id).build();
    bookingRepository.delete(booking);
    return CommonRes.<String>builder().result("OK").data("Delete booking success!!").build();
  }

  @Override
  @Transactional
  public CommonRes<String> checkIn(int id) {
    Booking booking =
        bookingRepository
            .findBookingByIdAndStatus(id, IN_PROGRESS.getValue())
            .orElseThrow(() -> new NotFoundException("bookingId"));
    booking.setStatus(BookingStatus.COMPLETED.getValue());
    bookingRepository.save(booking);

    Table table = tableRepository.findTableById(booking.getTable().getId());
    table.setStatus(Status.USING.getValue());
    tableRepository.save(table);

    return CommonRes.<String>builder().result("OK").data("Check-in success!!").build();
  }
}
