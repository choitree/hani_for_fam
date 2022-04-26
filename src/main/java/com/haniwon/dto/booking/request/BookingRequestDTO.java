package com.haniwon.dto.booking.request;

import com.haniwon.domain.Patient;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public class BookingRequestDTO {

    private final LocalDateTime bookingTime;

}
