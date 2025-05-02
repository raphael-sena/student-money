package com.lab.user_service.repositories;

import com.lab.user_service.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRespository extends JpaRepository<Student, Long> {
}
