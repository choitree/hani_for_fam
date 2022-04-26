package com.haniwon.repository.booking;

import com.haniwon.domain.Booking;

import java.time.LocalDateTime;
import java.util.Optional;

public interface BookingRepositoryCustom {

    public Optional<Booking> findByBookingTime(LocalDateTime bookingTime);
}
