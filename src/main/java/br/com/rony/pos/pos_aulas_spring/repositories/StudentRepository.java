package br.com.rony.pos.pos_aulas_spring.repositories;

import br.com.rony.pos.pos_aulas_spring.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
