package com.haniwon.domain;

import com.haniwon.dto.patient.request.PatientRequestDTO;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient", uniqueConstraints = { @UniqueConstraint(name = "UniqueNameAndBirthday", columnNames = { "name", "birthday" }) })
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long chartId;

    private String name;
    private String sex;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstVisit;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastVisit;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String memo;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Income> incomes = new ArrayList<>();

    public static Patient createPatient(PatientRequestDTO patientDTO) {
        if(patientDTO.getFirstVisit() == null) {
            return Patient.builder()
                    .chartId(patientDTO.getChartId())
                    .name(patientDTO.getName())
                    .sex(patientDTO.getSex())
                    .phone(patientDTO.getPhone())
                    .firstVisit(LocalDate.now())
                    .lastVisit(LocalDate.now())
                    .birthday(patientDTO.getBirthday())
                    .memo(patientDTO.getMemo())
                    .build();
        }
        return Patient.builder()
                .chartId(patientDTO.getChartId())
                .name(patientDTO.getName())
                .sex(patientDTO.getSex())
                .phone(patientDTO.getPhone())
                .firstVisit(patientDTO.getFirstVisit())
                .lastVisit(LocalDate.now())
                .birthday(patientDTO.getBirthday())
                .memo(patientDTO.getMemo())
                .build();
    }

    public void updatePatient(PatientRequestDTO patientRequestDTO) {
        this.chartId = patientRequestDTO.getChartId();
        this.name = patientRequestDTO.getName();
        this.sex = patientRequestDTO.getSex();
        this.phone = patientRequestDTO.getPhone();
        this.firstVisit = patientRequestDTO.getFirstVisit();
        this.lastVisit = patientRequestDTO.getLastVisit();
        this.birthday = patientRequestDTO.getBirthday();
        this.memo = patientRequestDTO.getMemo();
    }

    public void updateFirstVisitAndLastVisit(LocalDate firstVisit, LocalDate lastVisit) {
        this.lastVisit = lastVisit;
        this.firstVisit = firstVisit;
    }

    public void updateLastVisit(LocalDate lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", chartId=" + chartId +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", firstVisit=" + firstVisit +
                ", lastVisit=" + lastVisit +
                ", birthday=" + birthday +
                ", memo='" + memo + '\'' +
                '}';
    }
}
