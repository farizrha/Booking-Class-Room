package com.iuli.bookclassroom.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Software Engineering 2
    private String name;

    //2019
    private Integer year;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "subject")
    private List<SubjectStudyProgram> subjectStudyPrograms;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "subject")
    private List<StudentSubject> studentSubjects;


}
