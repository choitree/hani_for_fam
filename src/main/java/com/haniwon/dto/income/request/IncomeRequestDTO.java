package com.haniwon.dto.income.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeRequestDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long amount;
    private String name;
    private Boolean isCash;
    private Boolean isPay;
    private String symptom;
    private Boolean isAcupuncture;
    private String memo;
    private Long chartId;

}
