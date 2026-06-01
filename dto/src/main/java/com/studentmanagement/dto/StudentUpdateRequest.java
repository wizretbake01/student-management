package com.studentmanagement.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String department;
    private String phoneNumber;
    private String address;
    private String status;
}
