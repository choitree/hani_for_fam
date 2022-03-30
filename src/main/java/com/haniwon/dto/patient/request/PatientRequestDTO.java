package com.haniwon.dto.patient.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class PatientRequestDTO {

    private Integer pId;
    private String name;
    private String sex;
    private String phone;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstVisit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastVisit;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String memo;

    private List<Long> incomes;

}
