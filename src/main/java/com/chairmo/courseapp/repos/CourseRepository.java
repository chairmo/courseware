package com.chairmo.courseapp.repos;

import com.chairmo.courseapp.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
}
