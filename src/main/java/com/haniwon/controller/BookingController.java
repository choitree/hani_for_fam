package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.booking.request.BookingRequestDTO;
import com.haniwon.dto.booking.response.BookingResponseDTO;
import com.haniwon.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {


    private final Logger logger = LoggerFactory.getLogger(BookingController.class);

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponseDTO> showBooking(@PathVariable Long bookingId) {
        logger.info("예약 내역 1건 조회");
        return ResponseEntity.ok(bookingService.showBooking(bookingId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<BookingResponseDTO>> showBookingsByPatient(@PathVariable Long patientId) {
        logger.info("환자별 예약 조회");
        return ResponseEntity.ok(bookingService.showBookingByPatient(patientId));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO, @RequestParam Long patientId) {
        bookingService.createBooking(bookingRequestDTO, patientId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> updateBooking(@RequestBody BookingRequestDTO bookingRequestDTO, @PathVariable Long bookingId) {
        bookingService.updateBooking(bookingRequestDTO, bookingId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }
}
