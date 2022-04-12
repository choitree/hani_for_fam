package com.haniwon.dto.patient.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class MultiPatientResponseDTO {

    private final List<PatientResponseDTO> patients;

    public static MultiPatientResponseDTO from(List<PatientResponseDTO> patientsResponseDTO) {
        return MultiPatientResponseDTO.builder()
                .patients(patientsResponseDTO)
                .build();
    }
}
