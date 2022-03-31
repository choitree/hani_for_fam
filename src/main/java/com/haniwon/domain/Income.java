package com.haniwon.domain;

import com.haniwon.dto.income.request.IncomeRequestDTO;
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
    private Integer amount;
    private String name;
    private Boolean isCash;
    private Boolean isPay;
    private String symptom;
    private Boolean isAcupuncture;
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public static Income createIncome(IncomeRequestDTO incomeDTO, Patient patient) {
        return Income.builder()
                .date(incomeDTO.getDate())
                .name(incomeDTO.getName())
                .isCash(incomeDTO.getIsCash())
                .isPay(incomeDTO.getIsPay())
                .symptom(incomeDTO.getSymptom())
                .isAcupuncture(incomeDTO.getIsAcupuncture())
                .memo(incomeDTO.getMemo())
                .patient(patient)
                .build();
    }

}
