
package com.ravi.StudentWithDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(StudentRequestDTO studentRequestDTO);
    Student updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long id);
    List<Student> findByEmail(String email);
}
