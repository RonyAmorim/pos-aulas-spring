package br.com.rony.pos.pos_aulas_spring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "pos-puc")
public class TeacherController {

    @PreAuthorize("hasAnyRole({'ROLE_ADMIN','ROLE_TEACHER'})")
    @GetMapping("/teachers")
    public ResponseEntity<?> getAllTeachers() {
        // Implement the logic to retrieve all teachers
        return ResponseEntity.ok().body("List of teachers");
    }
}
