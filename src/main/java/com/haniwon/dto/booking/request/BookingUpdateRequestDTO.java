package com.haniwon.dto.booking.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public class BookingUpdateRequestDTO {

    private final LocalDateTime bookingTime;
}
