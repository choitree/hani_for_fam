package com.haniwon.repository.patient;

import com.haniwon.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {

    //chartId는 고유값이지만 id랑은 다른 값
    public Optional<Patient> findBychartId(Long pId);

    public Optional<Patient> findById(Long id);

    public Optional<Patient> findByNameAndBirthday(String name, LocalDate birthday);

    public List<Patient> findAllByName(String name);

    public List<Patient> findAllByNameContains(String name);

}
