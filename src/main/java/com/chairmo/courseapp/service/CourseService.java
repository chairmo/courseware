package com.chairmo.courseapp.service;

import com.chairmo.courseapp.domain.Course;
import com.chairmo.courseapp.domain.Lecturer;
import com.chairmo.courseapp.domain.Student;
import com.chairmo.courseapp.model.CourseDTO;
import com.chairmo.courseapp.repos.CourseRepository;
import com.chairmo.courseapp.repos.LecturerRepository;
import com.chairmo.courseapp.repos.StudentRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final LecturerRepository lecturerRepository;

    public CourseService(final CourseRepository courseRepository,
            final StudentRepository studentRepository,
            final LecturerRepository lecturerRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.lecturerRepository = lecturerRepository;
    }

    public List<CourseDTO> findAll() {
        return courseRepository.findAll()
                .stream()
                .map(course -> mapToDTO(course, new CourseDTO()))
                .collect(Collectors.toList());
    }

    public CourseDTO get(final Long id) {
        return courseRepository.findById(id)
                .map(course -> mapToDTO(course, new CourseDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final CourseDTO courseDTO) {
        final Course course = new Course();
        mapToEntity(courseDTO, course);
        return courseRepository.save(course).getId();
    }

    public void update(final Long id, final CourseDTO courseDTO) {
        final Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(courseDTO, course);
        courseRepository.save(course);
    }

    public void delete(final Long id) {
        courseRepository.deleteById(id);
    }

    private CourseDTO mapToDTO(final Course course, final CourseDTO courseDTO) {
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setCourseCode(course.getCourseCode());
        courseDTO.setCourseUnit(course.getCourseUnit());
        courseDTO.setOptions(course.getOptions());
        courseDTO.setStudent(course.getStudent() == null ? null : course.getStudent().getId());
        courseDTO.setLecturer(course.getLecturer() == null ? null : course.getLecturer().getId());
        return courseDTO;
    }

    private Course mapToEntity(final CourseDTO courseDTO, final Course course) {
        course.setName(courseDTO.getName());
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCourseUnit(courseDTO.getCourseUnit());
        course.setOptions(courseDTO.getOptions());
        if (courseDTO.getStudent() != null && (course.getStudent() == null || !course.getStudent().getId().equals(courseDTO.getStudent()))) {
            final Student student = studentRepository.findById(courseDTO.getStudent())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "offers not found"));
            course.setStudent(student);
        }
        if (courseDTO.getLecturer() != null && (course.getLecturer() == null || !course.getLecturer().getId().equals(courseDTO.getLecturer()))) {
            final Lecturer lecturer = lecturerRepository.findById(courseDTO.getLecturer())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lectures not found"));
            course.setLecturer(lecturer);
        }
        return course;
    }

}
