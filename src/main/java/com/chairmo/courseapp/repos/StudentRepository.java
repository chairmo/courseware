package com.chairmo.courseapp.repos;

import com.chairmo.courseapp.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
