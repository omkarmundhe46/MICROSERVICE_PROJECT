package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto toDto(Patient patient){
        PatientResponseDto patientDto = new PatientResponseDto();
        patientDto.setId(patient.getId().toString());
        patientDto.setName(patient.getName());
        patientDto.setAddress(patient.getAddress());
        patientDto.setEmail(patient.getEmail());
        patientDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDto;
    }

    public static Patient toModel(PatientRequestDto patientRequestDto ){
        Patient patient = new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return patient;

    }
}
