package study.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.homework.dto.StudentDTO;
import study.homework.entity.Student;
import study.homework.repository.StudentRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public void addStudent(StudentDTO studentDTO) {
        studentRepository.save(new Student(studentDTO.getName(), studentDTO.getAddress(), studentDTO.getSchool(), studentDTO.getMajor()));
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public void editStudent(Student student) {
        Student findStudent = studentRepository.findById(student.getId()).orElseThrow();
        findStudent.setName(student.getName());
        findStudent.setAddress(student.getAddress());
        findStudent.setSchool(student.getSchool());
        findStudent.setMajor(student.getMajor());
    }
}