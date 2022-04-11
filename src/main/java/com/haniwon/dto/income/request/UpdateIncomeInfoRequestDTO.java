package com.haniwon.dto.income.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class UpdateIncomeInfoRequestDTO {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Integer amount;
    private Boolean isCash;
    private Boolean isPay;
    private String symptom;
    private Boolean isAcupuncture;
    private String memo;
}
