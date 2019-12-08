package com.iuli.bookclassroom.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "study_program")
public class StudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "studyProgram")
    private List<SubjectStudyProgram> subjectStudyPrograms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
