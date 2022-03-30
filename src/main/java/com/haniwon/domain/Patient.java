package com.haniwon.domain;

import com.haniwon.dto.patient.request.PatientRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer pId;

    private String name;
    private String sex;
    private String phone;
    private LocalDate firstVisit;
    private LocalDate lastVisit;

    private LocalDate birthday;
    private String memo;

//    @OneToMany(mappedBy = "patient")
//    private List<Income> incomes = new ArrayList<>();

    public static Patient createPatient(PatientRequestDTO patientDTO) {
        if(patientDTO.getLastVisit() == null) {
            return Patient.builder()
                    .pId(patientDTO.getPId())
                    .name(patientDTO.getName())
                    .sex(patientDTO.getSex())
                    .phone(patientDTO.getPhone())
                    .firstVisit(patientDTO.getFirstVisit())
                    .lastVisit(LocalDate.now())
                    .birthday(patientDTO.getBirthday())
                    .memo(patientDTO.getMemo())
                    .build();
        }
        return Patient.builder()
                .pId(patientDTO.getPId())
                .name(patientDTO.getName())
                .sex(patientDTO.getSex())
                .phone(patientDTO.getPhone())
                .firstVisit(patientDTO.getFirstVisit())
                .lastVisit(patientDTO.getLastVisit())
                .birthday(patientDTO.getBirthday())
                .memo(patientDTO.getMemo())
                .build();
    }

    public void updatePatient(PatientRequestDTO patientRequestDTO) {
        this.pId = patientRequestDTO.getPId();
        this.name = patientRequestDTO.getName();
        this.sex = patientRequestDTO.getSex();
        this.phone = patientRequestDTO.getPhone();
        this.firstVisit = patientRequestDTO.getFirstVisit();
        this.lastVisit = patientRequestDTO.getLastVisit();
        this.birthday = patientRequestDTO.getBirthday();
        this.memo = patientRequestDTO.getMemo();
    }
}
