package com.haniwon.service;

import com.haniwon.domain.Booking;
import com.haniwon.domain.Patient;
import com.haniwon.dto.booking.request.BookingRequestDTO;
import com.haniwon.exception.BookingExistException;
import com.haniwon.repository.booking.BookingRepository;
import com.haniwon.repository.patient.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
}
