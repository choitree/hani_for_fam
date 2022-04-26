package com.haniwon.dto.booking.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haniwon.domain.Booking;
import com.haniwon.domain.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public class BookingResponseDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private final LocalDateTime bookingTime;
    private final String name;
    private final String phone;
    private final Long patientId;

    public static BookingResponseDTO from(Booking booking) {
        return BookingResponseDTO.builder()
                .bookingTime(booking.getBookingTime())
                .name(booking.getPatient().getName())
                .phone(booking.getPatient().getPhone())
                .patientId(booking.getPatient().getId())
                .build();
    }
}
