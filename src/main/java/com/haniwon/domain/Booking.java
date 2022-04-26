package com.haniwon.domain;

import com.haniwon.dto.booking.request.BookingRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime bookingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public static Booking createBooking(BookingRequestDTO bookingRequestDTO, Patient patient) {
        return Booking.builder()
                .bookingTime(bookingRequestDTO.getBookingTime())
                .patient(patient)
                .build();
    }

    public void updateBooking(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
