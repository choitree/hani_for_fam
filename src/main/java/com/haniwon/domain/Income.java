package com.haniwon.domain;

import com.haniwon.dto.income.request.IncomeRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomeInfoRequestDTO;
import com.haniwon.dto.income.request.UpdateIncomePatientRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Long amount;
    private Boolean isCash;
    private Boolean isPay;
    private String symptom;
    private Boolean isAcupuncture;
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    public static Income createIncome(IncomeRequestDTO incomeDTO, Patient patient) {
        return Income.builder()
                .date(incomeDTO.getDate())
                .amount(incomeDTO.getAmount())
                .isCash(incomeDTO.getIsCash())
                .isPay(incomeDTO.getIsPay())
                .symptom(incomeDTO.getSymptom())
                .isAcupuncture(incomeDTO.getIsAcupuncture())
                .memo(incomeDTO.getMemo())
                .patient(patient)
                .build();
    }

    public void updateIncomeInfo(UpdateIncomeInfoRequestDTO incomeDTO) {
        this.date = incomeDTO.getDate();
        this.amount = incomeDTO.getAmount();
        this.isCash = incomeDTO.getIsCash();
        this.isPay = incomeDTO.getIsPay();
        this.symptom = incomeDTO.getSymptom();
        this.isAcupuncture = incomeDTO.getIsAcupuncture();
        this.memo = incomeDTO.getMemo();
    }

    public void updateIncomePatient(Patient patient) {
        this.patient = patient;
    }

}
