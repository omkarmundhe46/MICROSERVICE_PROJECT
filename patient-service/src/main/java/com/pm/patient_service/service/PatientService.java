package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exceptions.EmailAlreadyExistsException;
import com.pm.patient_service.exceptions.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private  PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDto> patientResponseDtos = patients.stream().map(patient -> PatientMapper.toDto(patient)).toList();
        return patientResponseDtos;
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("A Patient with this email " + " already exists " + patientRequestDto.getEmail());
        }
        Patient newPatient = patientRepository.save(
           PatientMapper.toModel(patientRequestDto));
           return PatientMapper.toDto(newPatient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not Found With id : " + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDto.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A Patient with this email " + " already exists " + patientRequestDto.getEmail());
        }
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDto(updatedPatient);
    }

    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
