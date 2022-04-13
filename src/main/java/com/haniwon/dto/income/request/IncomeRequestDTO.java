package com.haniwon.dto.income.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class IncomeRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Long amount;
    private String name;
    private Boolean isCash;
    private Boolean isPay;
    private String symptom;
    private Boolean isAcupuncture;
    private String memo;
    private LocalDate birthday;

}
