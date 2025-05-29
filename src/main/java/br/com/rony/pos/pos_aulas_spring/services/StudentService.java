package br.com.rony.pos.pos_aulas_spring.services;

import br.com.rony.pos.pos_aulas_spring.entities.Student;
import br.com.rony.pos.pos_aulas_spring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student saveStudent(Student student){
        Objects.requireNonNull(student, "The student cannot be null.");
        if(student.getId() == null){
            throw new IllegalArgumentException("The ID cannot be null.");
        }
        if(student.getName() == null || student.getName().trim().isEmpty()){
            throw new IllegalArgumentException("the name can not be null or empty");
        }
        if (repository.existsById(student.getId())) {
            throw new IllegalArgumentException("Existing student with the same ID");
        }
        return repository.save(student);
    }

    public Iterable<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student updateStudent(Long id, Student student){
        Objects.requireNonNull(id, "The ID cannot be null.");
        Objects.requireNonNull(student, "The student cannot be null.");

        Student updatedStudent = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No student found with the given ID."));

        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }

        updatedStudent.setName(student.getName());
        return repository.save(updatedStudent);
    }

    public void deleteStudent(Long id) {
        Objects.requireNonNull(id, "The ID cannot be null.");
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No student found with the given ID.");
        }
        repository.deleteById(id);
    }
}
