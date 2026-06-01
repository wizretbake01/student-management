package com.studentmanagement.service;

import com.studentmanagement.dto.StudentCreateRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.dto.StudentUpdateRequest;
import com.studentmanagement.mapper.StudentMapper;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Student Service Tests")
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    private Student student;
    private StudentDTO studentDTO;
    private StudentCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .id(1L)
                .firstName("Arben")
                .lastName("Shala")
                .email("arben.shala@gmail.com")
                .dateOfBirth(LocalDate.of(2000, 5, 15))
                .department("Informatikë")
                .studentId("ST2024001")
                .phoneNumber("0691234567")
                .address("Prishtinë")
                .status(Student.StudentStatus.ACTIVE)
                .enrollmentYear(2024)
                .build();

        studentDTO = StudentDTO.builder()
                .id(1L)
                .firstName("Arben")
                .lastName("Shala")
                .email("arben.shala@gmail.com")
                .dateOfBirth(LocalDate.of(2000, 5, 15))
                .department("Informatikë")
                .studentId("ST2024001")
                .phoneNumber("0691234567")
                .address("Prishtinë")
                .status("ACTIVE")
                .enrollmentYear(2024)
                .build();

        createRequest = StudentCreateRequest.builder()
                .firstName("Arben")
                .lastName("Shala")
                .email("arben.shala@gmail.com")
                .dateOfBirth("2000-05-15")
                .department("Informatikë")
                .studentId("ST2024001")
                .phoneNumber("0691234567")
                .address("Prishtinë")
                .status("ACTIVE")
                .enrollmentYear(2024)
                .build();
    }

    @Test
    @DisplayName("Duhet të krijojë një student të ri me sukses")
    void testCreateStudent_Success() {
        // Arrange
        when(studentRepository.existsByEmail(anyString())).thenReturn(false);
        when(studentRepository.existsByStudentId(anyString())).thenReturn(false);
        when(studentMapper.toEntity(any(StudentCreateRequest.class))).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(any(Student.class))).thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.createStudent(createRequest);

        // Assert
        assertNotNull(result);
        assertEquals("Arben", result.getFirstName());
        assertEquals("arben.shala@gmail.com", result.getEmail());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    @DisplayName("Duhet të hedhë exception kur email ekziston")
    void testCreateStudent_EmailExists() {
        // Arrange
        when(studentRepository.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentService.createStudent(createRequest)
        );

        assertTrue(exception.getMessage().contains("email"));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Duhet të hedhë exception kur studentId ekziston")
    void testCreateStudent_StudentIdExists() {
        // Arrange
        when(studentRepository.existsByEmail(anyString())).thenReturn(false);
        when(studentRepository.existsByStudentId(anyString())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> studentService.createStudent(createRequest)
        );

        assertTrue(exception.getMessage().contains("ID"));
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    @DisplayName("Duhet të kthejë të gjithë studentët")
    void testGetAllStudents() {
        // Arrange
        List<Student> students = Arrays.asList(student);
        List<StudentDTO> studentDTOs = Arrays.asList(studentDTO);

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toDTOList(students)).thenReturn(studentDTOs);

        // Act
        List<StudentDTO> result = studentService.getAllStudents();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Duhet të kthejë student sipas ID-së")
    void testGetStudentById_Success() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.getStudentById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Arben", result.getFirstName());
    }

    @Test
    @DisplayName("Duhet të hedhë exception kur studenti nuk gjendet sipas ID-së")
    void testGetStudentById_NotFound() {
        // Arrange
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    @DisplayName("Duhet të përditësojë një student")
    void testUpdateStudent_Success() {
        // Arrange
        StudentUpdateRequest updateRequest = StudentUpdateRequest.builder()
                .firstName("Arben Updated")
                .email("arben.new@gmail.com")
                .build();

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.existsByEmail("arben.new@gmail.com")).thenReturn(false);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentMapper.toDTO(student)).thenReturn(studentDTO);

        // Act
        StudentDTO result = studentService.updateStudent(1L, updateRequest);

        // Assert
        assertNotNull(result);
        verify(studentMapper, times(1)).updateEntityFromRequest(updateRequest, student);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    @DisplayName("Duhet të fshijë një student")
    void testDeleteStudent_Success() {
        // Arrange
        when(studentRepository.existsById(1L)).thenReturn(true);

        // Act
        studentService.deleteStudent(1L);

        // Assert
        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Duhet të hedhë exception kur përpiqet të fshijë student që nuk ekziston")
    void testDeleteStudent_NotFound() {
        // Arrange
        when(studentRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(1L));
        verify(studentRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Duhet të kthejë numrin total të studentëve")
    void testGetTotalStudentCount() {
        // Arrange
        when(studentRepository.count()).thenReturn(5L);

        // Act
        long count = studentService.getTotalStudentCount();

        // Assert
        assertEquals(5L, count);
        verify(studentRepository, times(1)).count();
    }
}
