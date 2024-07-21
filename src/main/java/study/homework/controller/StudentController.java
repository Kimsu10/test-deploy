package study.homework.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.homework.dto.StudentDTO;
import study.homework.entity.Student;
import study.homework.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostConstruct
    public void init() {
        studentService.addStudent(new StudentDTO("수정", "경기도", "비트대", "네이버클라우드"));
        studentService.addStudent(new StudentDTO("둘리", "북극", "서울대", "발굴학과"));
        studentService.addStudent(new StudentDTO("랑이", "경기도", "고려대", "역사학과"));
        studentService.addStudent(new StudentDTO("스티븐", "강원도", "한국외국어대학교", "정치외교학과"));
        studentService.addStudent(new StudentDTO("스왈로브스키", "블라디보스토크", "볼셰비키대학교", "혁명학과"));
    }

    @ResponseBody
    @GetMapping("/search")
    public List<StudentDTO> searchStudents(@RequestParam String name) {
        List<Student> students = studentService.getStudentsByName(name);
        return students.stream().map(student -> new StudentDTO(
                student.getName(),
                student.getAddress(),
                student.getSchool(),
                student.getMajor()
        )).collect(Collectors.toList());
    }

    @ResponseBody
    @PostMapping("/students/delete")
    public boolean deleteStudent(@RequestParam Long id) {
        try {
            studentService.deleteStudentById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute StudentDTO studentDTO) {
        studentService.addStudent(studentDTO);
        return "redirect:/";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentService.editStudent(student);
        return "redirect:/";
    }
}