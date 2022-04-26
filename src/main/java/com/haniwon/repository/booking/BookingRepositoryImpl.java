package com.haniwon.repository.booking;

import com.haniwon.domain.Booking;
import com.haniwon.domain.QBooking;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public Optional<Booking> findByBookingTime(LocalDateTime bookingTime) {
        return Optional.ofNullable(queryFactory.selectFrom(QBooking.booking)
                .where(QBooking.booking.bookingTime.eq(bookingTime))
                .fetchOne());
    }
}
