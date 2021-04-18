package com.chairmo.courseapp.service;

import com.chairmo.courseapp.domain.Lecturer;
import com.chairmo.courseapp.model.LecturerDTO;
import com.chairmo.courseapp.repos.LecturerRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class LecturerService {

    private final LecturerRepository lecturerRepository;

    public LecturerService(final LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    public List<LecturerDTO> findAll() {
        return lecturerRepository.findAll()
                .stream()
                .map(lecturer -> mapToDTO(lecturer, new LecturerDTO()))
                .collect(Collectors.toList());
    }

    public LecturerDTO get(final Long id) {
        return lecturerRepository.findById(id)
                .map(lecturer -> mapToDTO(lecturer, new LecturerDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final LecturerDTO lecturerDTO) {
        final Lecturer lecturer = new Lecturer();
        mapToEntity(lecturerDTO, lecturer);
        return lecturerRepository.save(lecturer).getId();
    }

    public void update(final Long id, final LecturerDTO lecturerDTO) {
        final Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(lecturerDTO, lecturer);
        lecturerRepository.save(lecturer);
    }

    public void delete(final Long id) {
        lecturerRepository.deleteById(id);
    }

    private LecturerDTO mapToDTO(final Lecturer lecturer, final LecturerDTO lecturerDTO) {
        lecturerDTO.setId(lecturer.getId());
        lecturerDTO.setDetails(lecturer.getDetails());
        return lecturerDTO;
    }

    private Lecturer mapToEntity(final LecturerDTO lecturerDTO, final Lecturer lecturer) {
        lecturer.setDetails(lecturerDTO.getDetails());
        return lecturer;
    }

}
