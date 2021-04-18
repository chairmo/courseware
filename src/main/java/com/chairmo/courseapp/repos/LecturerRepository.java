package com.chairmo.courseapp.repos;

import com.chairmo.courseapp.domain.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
}
