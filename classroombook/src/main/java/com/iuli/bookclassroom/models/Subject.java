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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<SubjectStudyProgram> getSubjectStudyPrograms() {
        return subjectStudyPrograms;
    }

    public void setSubjectStudyPrograms(List<SubjectStudyProgram> subjectStudyPrograms) {
        this.subjectStudyPrograms = subjectStudyPrograms;
    }

    public List<StudentSubject> getStudentSubjects() {
        return studentSubjects;
    }

    public void setStudentSubjects(List<StudentSubject> studentSubjects) {
        this.studentSubjects = studentSubjects;
    }
}
