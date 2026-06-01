package com.studentmanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth; // Format: yyyy-MM-dd
    private String department;
    private String studentId;
    private String phoneNumber;
    private String address;
    private String status;
    private Integer enrollmentYear;
}
