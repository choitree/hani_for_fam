package com.haniwon.repository.booking;

import com.haniwon.domain.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepositoryCustom {

    public Optional<Booking> findByBookingTime(LocalDateTime bookingTime);

    public List<Booking> findAllBookingByDate(LocalDate date);

    public List<Booking> findAllBookingByWeek(LocalDate startDate, LocalDate endDate);

}
