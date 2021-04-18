package com.chairmo.courseapp.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentDTO {

    private Long id;

    @NotNull
    @Valid
    private Person details;

    @NotNull
    private Stage level;

}
