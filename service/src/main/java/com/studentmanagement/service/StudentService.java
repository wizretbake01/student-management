package com.studentmanagement.service;

import com.studentmanagement.dto.StudentCreateRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.dto.StudentUpdateRequest;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentDTO createStudent(StudentCreateRequest request) {
        log.info("Krijimi i studentit të ri: {}", request.getEmail());

        // Kontrollo nëse email ekziston
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Një student me këtë email ekziston tashmë");
        }

        // Kontrollo nëse studentId ekziston
        if (studentRepository.existsByStudentId(request.getStudentId())) {
            throw new IllegalArgumentException("Një student me këtë ID ekziston tashmë");
        }

        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentRepository.save(student);

        log.info("Studenti u krijua me sukses: {}", savedStudent.getId());
        return studentMapper.toDTO(savedStudent);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        log.info("Marrja e të gjithë studentëve");
        List<Student> students = studentRepository.findAll();
        return studentMapper.toDTOList(students);
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        log.info("Marrja e studentit me ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studenti nuk u gjet me ID: " + id));
        return studentMapper.toDTO(student);
    }

    @Transactional(readOnly = true)
    public StudentDTO getStudentByEmail(String email) {
        log.info("Marrja e studentit me email: {}", email);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Studenti nuk u gjet me email: " + email));
        return studentMapper.toDTO(student);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByDepartment(String department) {
        log.info("Marrja e studentëve të departamentit: {}", department);
        List<Student> students = studentRepository.findByDepartment(department);
        return studentMapper.toDTOList(students);
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByStatus(String status) {
        log.info("Marrja e studentëve me status: {}", status);
        Student.StudentStatus studentStatus = Student.StudentStatus.valueOf(status.toUpperCase());
        List<Student> students = studentRepository.findByStatus(studentStatus);
        return studentMapper.toDTOList(students);
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentUpdateRequest request) {
        log.info("Përditësimi i studentit me ID: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Studenti nuk u gjet me ID: " + id));

        // Kontrollo email nëse është ndryshuar
        if (request.getEmail() != null && !request.getEmail().equals(student.getEmail())) {
            if (studentRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Një student me këtë email ekziston tashmë");
            }
        }

        studentMapper.updateEntityFromRequest(request, student);
        Student updatedStudent = studentRepository.save(student);

        log.info("Studenti u përditësua me sukses: {}", id);
        return studentMapper.toDTO(updatedStudent);
    }

    @Transactional
    public void deleteStudent(Long id) {
        log.info("Fshirja e studentit me ID: {}", id);

        if (!studentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Studenti nuk u gjet me ID: " + id);
        }

        studentRepository.deleteById(id);
        log.info("Studenti u fshi me sukses: {}", id);
    }

    @Transactional(readOnly = true)
    public long getTotalStudentCount() {
        return studentRepository.count();
    }

    @Transactional(readOnly = true)
    public List<StudentDTO> getStudentsByEnrollmentYear(Integer year) {
        log.info("Marrja e studentëve të vitit: {}", year);
        List<Student> students = studentRepository.findByEnrollmentYear(year);
        return studentMapper.toDTOList(students);
    }
}
