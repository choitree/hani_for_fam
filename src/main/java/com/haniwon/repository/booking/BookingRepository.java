package com.haniwon.repository.booking;

import com.haniwon.domain.Booking;
import com.haniwon.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, BookingRepositoryCustom {

    public List<Booking> findAllByPatient(Patient patient);
}
