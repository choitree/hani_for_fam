package com.haniwon.repository;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static com.haniwon.domain.QIncome.income;

public interface IncomeRepositoryCustom {

    public List<Income> findAllByPatient(Patient patient);

    public Optional<Income> findLastVisitIncomeByPatient(Patient patient);

    public LocalDate findLastVisitByPatient(Patient patient);

    public LocalDate findFirstVisitByPatient(Patient patient);

    public Long countVisitByPatient(Patient patient);

    public List<Income> findAllByMonth(YearMonth yearMonth);

    public List<Income> findAllByYear(Year year);

    public List<Income> findAllByPeriod(LocalDate date1, LocalDate date2);

//    public IncomeCaseSumResponseDTO findAllIncomeAndSummeryByPatient(Patient patient);

    }
