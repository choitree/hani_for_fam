package com.haniwon.service;

import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import com.haniwon.repository.IncomeRepository;
import com.haniwon.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class IncomeService {

      private final PatientRepository patientRepository;
    private final IncomeRepository incomeRepository;

    public IncomeService(PatientRepository patientRepository, IncomeRepository incomeRepository) {
        this.patientRepository = patientRepository;
        this.incomeRepository = incomeRepository;
    }

    @Transactional
    public void addIncome(IncomeRequestDTO incomeRequestDTO, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new NoSuchElementException("해당 환자가 존재하지 않아 등록이 불가합니다."));
        Income income = Income.createIncome(incomeRequestDTO, patient);
        Patient visitPatient = patientRepository.findByNameAndBirthday(incomeRequestDTO.getName(), incomeRequestDTO.getBirthday()).orElseThrow(() -> new NoSuchElementException("해당 환자가 존재하지 않아 등록이 불가합니다."));
        if(patient != visitPatient) {
            throw new IllegalArgumentException("다른 환자의 매출 정보는 등록할 수 없습니다.");
        }
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRequestDTO.getDate();
       if(incomeRepository.findLastVisitIncomeByPatient(patient).isPresent()) {
           lastVisit = incomeRepository.findLastVisitIncomeByPatient(patient).get().getDate();
       }
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

    @Transactional
    public void updateIncomeInfo(UpdateIncomeInfoRequestDTO incomeInfoRequestDTO, Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("수정할 매출에 정보가 존재하지 않습니다."));
        Patient patient = income.getPatient();
        income.updateIncomeInfo(incomeInfoRequestDTO);
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(patient);
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

    @Transactional
    public void updateIncomePatient(UpdateIncomePatientRequestDTO incomePatientRequestDTO, Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("수정할 매출에 정보가 존재하지 않습니다."));
        Patient oldPatient = income.getPatient();
        Patient newPatient = patientRepository.findByNameAndBirthday(incomePatientRequestDTO.getName(), incomePatientRequestDTO.getBirthday()).orElseThrow(() -> new NoSuchElementException("변경하려는 환자 정보가 존재하지 않습니다."));
        income.updateIncomePatient(newPatient);
        incomeRepository.save(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(newPatient);
        newPatient.updateLastVisit(lastVisit);
        patientRepository.save(newPatient);
        lastVisit = incomeRepository.findLastVisitByPatient(oldPatient);
        oldPatient.updateLastVisit(lastVisit);
        patientRepository.save(oldPatient);
    }

    @Transactional
    public void deleteIncome(Long incomeId) {
        Income income = incomeRepository.findById(incomeId).orElseThrow(() -> new NoSuchElementException("삭제할 매출에 정보가 존재하지 않습니다."));
        Patient patient = income.getPatient();
        incomeRepository.delete(income);
        LocalDate lastVisit = incomeRepository.findLastVisitByPatient(patient);
        patient.updateLastVisit(lastVisit);
        patientRepository.save(patient);
    }

}
