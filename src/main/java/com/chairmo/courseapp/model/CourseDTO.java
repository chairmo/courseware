package com.chairmo.courseapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CourseDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String courseCode;

    private Integer courseUnit;

    private Options options;

    @NotNull
    private Long lecturer;

    @NotNull
    private Long student;

}
