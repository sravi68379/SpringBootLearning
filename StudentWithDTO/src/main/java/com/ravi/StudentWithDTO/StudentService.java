
package com.ravi.StudentWithDTO;
import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentResponseDTO> getAllStudents();
    Optional<StudentResponseDTO> getStudentById(Long id);
    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long id);
    List<StudentResponseDTO> findByEmail(String email);
}
