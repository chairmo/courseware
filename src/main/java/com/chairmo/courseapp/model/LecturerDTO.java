package com.chairmo.courseapp.model;

import javax.validation.Valid;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LecturerDTO {

    private Long id;

    @Valid
    private Person details;

}
