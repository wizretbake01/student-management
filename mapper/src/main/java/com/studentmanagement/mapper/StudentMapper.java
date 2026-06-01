package com.studentmanagement.mapper;

import com.studentmanagement.dto.StudentCreateRequest;
import com.studentmanagement.dto.StudentDTO;
import com.studentmanagement.dto.StudentUpdateRequest;
import com.studentmanagement.model.Student;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "status", source = "status", qualifiedByName = "statusToString")
    StudentDTO toDTO(Student student);

    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToLocalDate")
    Student toEntity(StudentDTO dto);

    List<StudentDTO> toDTOList(List<Student> students);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToLocalDate")
    Student toEntity(StudentCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "studentId", ignore = true)
    @Mapping(target = "enrollmentYear", ignore = true)
    @Mapping(target = "status", source = "status", qualifiedByName = "stringToStatus")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", qualifiedByName = "stringToLocalDate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(StudentUpdateRequest request, @MappingTarget Student student);

    @Named("statusToString")
    default String statusToString(Student.StudentStatus status) {
        return status != null ? status.name() : null;
    }

    @Named("stringToStatus")
    default Student.StudentStatus stringToStatus(String status) {
        return status != null ? Student.StudentStatus.valueOf(status.toUpperCase()) : null;
    }

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String date) {
        return date != null ? LocalDate.parse(date) : null;
    }
}
