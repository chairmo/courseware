package com.chairmo.courseapp.rest;

import com.chairmo.courseapp.model.LecturerDTO;
import com.chairmo.courseapp.service.LecturerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/lecturers", produces = MediaType.APPLICATION_JSON_VALUE)
public class LecturerController {

    private final LecturerService lecturerService;

    public LecturerController(final LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    @GetMapping
    public ResponseEntity<List<LecturerDTO>> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LecturerDTO> getLecturer(@PathVariable final Long id) {
        return ResponseEntity.ok(lecturerService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createLecturer(@RequestBody @Valid final LecturerDTO lecturerDTO) {
        return new ResponseEntity<>(lecturerService.create(lecturerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLecturer(@PathVariable final Long id,
            @RequestBody @Valid final LecturerDTO lecturerDTO) {
        lecturerService.update(id, lecturerDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable final Long id) {
        lecturerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
