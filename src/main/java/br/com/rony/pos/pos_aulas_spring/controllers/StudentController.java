package br.com.rony.pos.pos_aulas_spring.controllers;

import br.com.rony.pos.pos_aulas_spring.entities.Student;
import br.com.rony.pos.pos_aulas_spring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos-puc")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<?> getAllStudents(){
        try {
            return ResponseEntity.ok(studentService.getAllStudents());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving students: " + e.getMessage());
        }
    }

    @PostMapping("/student")
    public ResponseEntity<?> addStudent(@Validated @RequestBody Student student){
        try {
            Student newStudent = studentService.saveStudent(student);
            if (newStudent != null && newStudent.getId() != null) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Student created successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student created failed: invalid data");
            }
        } catch (IllegalArgumentException e){
            return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body(e.getMessage());
        }
    }

    @PatchMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @Validated @RequestBody Student student){
        try{
            Student updatedStudent = studentService.updateStudent(id, student);
            return ResponseEntity.ok("Student updated successfully");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        try{
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
