package com.pm.patient_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.lang.NonNull;

import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NonNull
    private String name;

    @NonNull
    @Email
    @Column(unique = true)
    private String email;

    @NonNull
    private String address;

    @NonNull
    private LocalDate dateOfBirth;

    @NonNull
    private LocalDate registeredDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@NonNull @Email String email) {
        this.email = email;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    @NonNull
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NonNull LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @NonNull
    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(@NonNull LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
}

