package com.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Emri është i detyrueshëm")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "Mbiemri është i detyrueshëm")
    @Column(nullable = false)
    private String lastName;

    @Email(message = "Email duhet të jetë valid")
    @NotBlank(message = "Email është i detyrueshëm")
    @Column(nullable = false, unique = true)
    private String email;

    @Past(message = "Data e lindjes duhet të jetë në të kaluarën")
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String studentId;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentStatus status;

    @Column(nullable = false)
    private Integer enrollmentYear;

    public enum StudentStatus {
        ACTIVE,
        INACTIVE,
        GRADUATED,
        SUSPENDED
    }
}
