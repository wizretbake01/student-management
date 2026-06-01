package com.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Emri është i detyrueshëm")
    private String firstName;

    @NotBlank(message = "Mbiemri është i detyrueshëm")
    private String lastName;

    @Email(message = "Email duhet të jetë valid")
    @NotBlank(message = "Email është i detyrueshëm")
    private String email;

    @Past(message = "Data e lindjes duhet të jetë në të kaluarën")
    @NotNull(message = "Data e lindjes është e detyrueshme")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Departamenti është i detyrueshëm")
    private String department;

    @NotBlank(message = "ID e studentit është e detyrueshme")
    private String studentId;

    private String phoneNumber;

    private String address;

    @NotNull(message = "Statusi është i detyrueshëm")
    private String status;

    @NotNull(message = "Viti i regjistrimit është i detyrueshëm")
    private Integer enrollmentYear;
}
