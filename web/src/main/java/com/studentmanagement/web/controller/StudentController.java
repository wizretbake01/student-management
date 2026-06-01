package com.studentmanagement.web.controller;

import com.studentmanagement.dto.StudentCreateRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.dto.StudentUpdateRequest;
import com.studentmanagement.excel.StudentExcelExporter;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;
    private final StudentExcelExporter excelExporter;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentCreateRequest request) {
        log.info("REST: Krijimi i studentit të ri");
        StudentDTO createdStudent = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        log.info("REST: Marrja e të gjithë studentëve");
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long id) {
        log.info("REST: Marrja e studentit me ID: {}", id);
        StudentDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentDTO> getStudentByEmail(@PathVariable("email") String email) {
        log.info("REST: Marrja e studentit me email: {}", email);
        StudentDTO student = studentService.getStudentByEmail(email);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentDTO>> getStudentsByDepartment(@PathVariable("department") String department) {
        log.info("REST: Marrja e studentëve të departamentit: {}", department);
        List<StudentDTO> students = studentService.getStudentsByDepartment(department);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<StudentDTO>> getStudentsByStatus(@PathVariable("status") String status) {
        log.info("REST: Marrja e studentëve me status: {}", status);
        List<StudentDTO> students = studentService.getStudentsByStatus(status);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<StudentDTO>> getStudentsByEnrollmentYear(@PathVariable("year") Integer year) {
        log.info("REST: Marrja e studentëve të vitit: {}", year);
        List<StudentDTO> students = studentService.getStudentsByEnrollmentYear(year);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(
            @PathVariable("id") Long id,
            @Valid @RequestBody StudentUpdateRequest request) {
        log.info("REST: Përditësimi i studentit me ID: {}", id);
        StudentDTO updatedStudent = studentService.updateStudent(id, request);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long id) {
        log.info("REST: Fshirja e studentit me ID: {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalStudentCount() {
        log.info("REST: Marrja e numrit total të studentëve");
        long count = studentService.getTotalStudentCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportStudentsToExcel() throws IOException {
        log.info("REST: Eksportimi i studentëve në Excel");
        List<StudentDTO> students = studentService.getAllStudents();
        byte[] excelData = excelExporter.exportStudentsToExcel(students);

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "studente_" + timestamp + ".xlsx";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelData);
    }
}
