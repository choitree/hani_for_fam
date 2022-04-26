package com.haniwon.repository.booking;

import com.haniwon.domain.Booking;
import com.haniwon.domain.QBooking;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public Optional<Booking> findByBookingTime(LocalDateTime bookingTime) {
        return Optional.ofNullable(queryFactory
                            .selectFrom(QBooking.booking)
                            .where(QBooking.booking.bookingTime.eq(bookingTime))
                            .fetchOne());
    }

    public List<Booking> findAllBookingByWeek(LocalDate startDate, LocalDate endDate) {
        return queryFactory
                .selectFrom(QBooking.booking)
                .where(QBooking.booking.bookingTime.between(startDate.atStartOfDay(), endDate.atStartOfDay()))
                .fetch();
    }
}
