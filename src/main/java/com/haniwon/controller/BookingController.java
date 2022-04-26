package com.haniwon.controller;

import com.haniwon.dto.ResponseDTO;
import com.haniwon.dto.booking.request.BookingRequestDTO;
import com.haniwon.dto.booking.request.BookingUpdateRequestDTO;
import com.haniwon.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{patientId}")
    public ResponseEntity<ResponseDTO> createBooking(@RequestBody BookingRequestDTO bookingRequestDTO, @PathVariable Long patientId) {
        bookingService.createBooking(bookingRequestDTO, patientId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<ResponseDTO> updateBooking(@RequestBody BookingUpdateRequestDTO bookingUpdateRequestDTO, @PathVariable Long bookingId) {
        bookingService.updateBooking(bookingUpdateRequestDTO, bookingId);
        return ResponseEntity.ok(new ResponseDTO("ok"));
    }
}
