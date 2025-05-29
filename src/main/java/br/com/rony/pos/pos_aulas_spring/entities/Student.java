package br.com.rony.pos.pos_aulas_spring.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @Column(name = "student_name", nullable=false, length = 100)
    private String name;
}
