package com.chairmo.courseapp.domain;

import com.chairmo.courseapp.model.Person;
import com.chairmo.courseapp.model.Stage;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


@Entity
@TypeDefs(@TypeDef(name = "json", typeClass = JsonStringType.class))
@Getter
@Setter
public class Student {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "json")
    @Type(type = "json")
    private Person details;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Stage level;

    @OneToMany(mappedBy = "student")
    private Set<Course> courses;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }

}
