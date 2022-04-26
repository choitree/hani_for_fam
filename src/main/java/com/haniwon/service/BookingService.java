package com.haniwon.service;

import com.haniwon.domain.Booking;
import com.haniwon.domain.Patient;
import com.haniwon.dto.booking.request.BookingRequestDTO;
import com.haniwon.dto.booking.response.BookingResponseDTO;
import com.haniwon.exception.BookingExistException;
import com.haniwon.repository.booking.BookingRepository;
import com.haniwon.repository.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PatientRepository patientRepository;

    public BookingService(BookingRepository bookingRepository, PatientRepository patientRepository) {
        this.bookingRepository = bookingRepository;
        this.patientRepository = patientRepository;
    }

    public void createBooking(BookingRequestDTO bookingRequestDTO, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("예약하려는 환자 정보가 존재하지 않습니다."));
        Booking booking = Booking.createBooking(bookingRequestDTO, patient);
        if(bookingRepository.findByBookingTime(bookingRequestDTO.getBookingTime()).isPresent()) {
            throw new BookingExistException("예약하려는 시간에 이미 예약이 존재합니다.");
        }
        bookingRepository.save(booking);
    }

    public void updateBooking(BookingRequestDTO bookingRequestDTO, Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NoSuchElementException("수정하려는 예약 건이 존재하지 않습니다."));
        if(bookingRepository.findByBookingTime(bookingRequestDTO.getBookingTime()).isPresent()) {
            throw new BookingExistException("변경하려는 시간이 이미 예약한 환자가 있습니다.");
        }
        booking.updateBooking(bookingRequestDTO.getBookingTime());
        bookingRepository.save(booking);
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NoSuchElementException("삭제하려는 예약 정보가 존재하지 않습니다."));
        bookingRepository.delete(booking);
    }

    public BookingResponseDTO showBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new NoSuchElementException("조회하려는 예약 건이 존재하지 않습니다."));
        return BookingResponseDTO.from(booking);
    }

    public List<BookingResponseDTO> showBookingByPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("조회하려는 환자 정보가 존재하지 않습니다."));
        List<Booking> bookings = bookingRepository.findAllByPatient(patient);
        return bookings.stream()
                .map(booking -> showBooking(booking.getId()))
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> showBookingsByDay(LocalDate date) {
        List<Booking> bookings = bookingRepository.findAllBookingByDate(date);
        return bookings.stream()
                .map(booking -> showBooking(booking.getId()))
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> showWeekBookings(LocalDate startDate, LocalDate endDate) {
        List<Booking> bookings = bookingRepository.findAllBookingByWeek(startDate, endDate);
        return bookings.stream()
                .map(booking -> showBooking(booking.getId()))
                .collect(Collectors.toList());
    }
}
