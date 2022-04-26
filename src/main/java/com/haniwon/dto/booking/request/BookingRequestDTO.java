package com.haniwon.dto.booking.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class BookingRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime bookingTime;

}
