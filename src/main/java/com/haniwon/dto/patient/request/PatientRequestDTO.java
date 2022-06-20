package com.haniwon.dto.patient.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.haniwon.domain.Income;
import com.haniwon.domain.Patient;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatientRequestDTO {
    @NotNull(message = "차트번호를 입력하세요")
    private Long chartId;
    @NotBlank(message = "이름을 입력하세요")
    private String name;
    @NotBlank(message = "성별을 입력하세요")
    private String sex;
    @NotBlank(message = "휴대폰 번호를 입력하세요")
    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstVisit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastVisit;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "생일을 입력하세요")
    private LocalDate birthday;

    private String memo;

}
