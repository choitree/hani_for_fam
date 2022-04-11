package com.haniwon.repository;

import com.haniwon.domain.Patient;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>, PatientRepositoryCustom {

    //pId는 고유값이지만 id랑은 다른 값
    public Optional<Patient> findBychartId(Integer pId);

    public Optional<Patient> findById(Long id);

    public Optional<Patient> findByNameAndBirthday(String name, LocalDate birthday);

}
