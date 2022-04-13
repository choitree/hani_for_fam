package com.haniwon.repository;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepositoryCustom {

    public List<Income> findAllByPatient(Patient patient);

    public Optional<Income> findLastVisitIncomeByPatient(Patient patient);

    public LocalDate findLastVisitByPatient(Patient patient);

    public LocalDate findFirstVisitByPatient(Patient patient);

    public Long countVisitByPatient(Patient patient);

//    public IncomeCaseSumResponseDTO findAllIncomeAndSummeryByPatient(Patient patient);

    }
