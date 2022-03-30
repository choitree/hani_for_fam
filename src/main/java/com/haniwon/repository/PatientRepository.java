package com.haniwon.repository;

import com.haniwon.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

    public Optional<Patient> findBypId(Integer pId);
}
