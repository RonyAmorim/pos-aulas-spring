package br.com.rony.pos.pos_aulas_spring.services;

import br.com.rony.pos.pos_aulas_spring.entities.StudentEntity;
import br.com.rony.pos.pos_aulas_spring.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public StudentEntity saveStudent(StudentEntity studentEntity){
        Objects.requireNonNull(studentEntity, "The student cannot be null.");
        if(studentEntity.getName() == null || studentEntity.getName().trim().isEmpty()){
            throw new IllegalArgumentException("the name can not be null or empty");
        }
        if (studentEntity.getId() != null && repository.existsById(studentEntity.getId())) {
            throw new IllegalArgumentException("Existing student with the same ID. Use update for existing students.");
        }
        return repository.save(studentEntity);
    }

    public Iterable<StudentEntity> getAllStudents() {
        return repository.findAll();
    }

    public StudentEntity updateStudent(Long id, StudentEntity studentEntity){
        Objects.requireNonNull(id, "The ID cannot be null.");
        Objects.requireNonNull(studentEntity, "The student cannot be null.");

        StudentEntity updatedStudentEntity = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("No student found with the given ID."));

        if (studentEntity.getName() == null || studentEntity.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be null or empty.");
        }

        updatedStudentEntity.setName(studentEntity.getName());
        return repository.save(updatedStudentEntity);
    }

    public void deleteStudent(Long id) {
        Objects.requireNonNull(id, "The ID cannot be null.");
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("No student found with the given ID.");
        }
        repository.deleteById(id);
    }
}