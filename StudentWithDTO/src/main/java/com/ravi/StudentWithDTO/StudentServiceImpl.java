
package com.ravi.StudentWithDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return repository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentResponseDTO> getStudentById(Long id) {
        return repository.findById(id)
                .map(this::convertToResponseDTO);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student student = convertToEntity(studentRequestDTO);
        Student savedStudent = repository.save(student);
        return convertToResponseDTO(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        return repository.findById(id)
                .map(existingStudent -> {
                    String[] nameParts = studentRequestDTO.getName().split(" ", 2);
                    existingStudent.setFirstName(nameParts[0]);
                    existingStudent.setLastName(nameParts.length > 1 ? nameParts[1] : "");
                    existingStudent.setEmail(studentRequestDTO.getEmail());
                    existingStudent.setAge(studentRequestDTO.getAge());
                    Student updatedStudent = repository.save(existingStudent);
                    return convertToResponseDTO(updatedStudent);
                }).orElse(null);
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<StudentResponseDTO> findByEmail(String email) {
        return repository.findByEmail(email).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private Student convertToEntity(StudentRequestDTO dto) {
        Student student = new Student();
        String[] nameParts = dto.getName().split(" ", 2);
        student.setFirstName(nameParts[0]);
        student.setLastName(nameParts.length > 1 ? nameParts[1] : "");
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        return student;
    }

    private StudentResponseDTO convertToResponseDTO(Student student) {
        return new StudentResponseDTO(student.getId(), student.getFirstName() + " " + student.getLastName(), student.getEmail(), student.getAge());
    }
}
