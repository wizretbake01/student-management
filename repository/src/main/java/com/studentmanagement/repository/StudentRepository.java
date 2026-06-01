package com.studentmanagement.repository;

import com.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    Optional<Student> findByStudentId(String studentId);

    List<Student> findByDepartment(String department);

    List<Student> findByStatus(Student.StudentStatus status);

    List<Student> findByEnrollmentYear(Integer year);

    boolean existsByEmail(String email);

    boolean existsByStudentId(String studentId);
}
