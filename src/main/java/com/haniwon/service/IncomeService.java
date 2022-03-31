package com.haniwon.service;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.repository.IncomeRepository;
import com.haniwon.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class IncomeService {

    private final IncomeRepository incomeRepository;
    private final PatientRepository patientRepository;

    public IncomeService(IncomeRepository incomeRepository, PatientRepository patientRepository) {
        this.incomeRepository = incomeRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public void addIncome(IncomeRequestDTO incomeRequestDTO, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("해당 환자가 존재하지 않아 등록이 불가합니다."));
        Income income = Income.createIncome(incomeRequestDTO, patient);
        patient.updateLastVisit(income.getDate());
        patientRepository.save(patient);
        incomeRepository.save(income);
    }
}
