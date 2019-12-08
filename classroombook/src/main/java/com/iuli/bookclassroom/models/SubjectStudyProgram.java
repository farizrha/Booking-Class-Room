package com.iuli.bookclassroom.models;

import javax.persistence.*;

@Entity
@Table (name = "subject_study_program")
public class SubjectStudyProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subject.class)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = StudyProgram.class)
    @JoinColumn(name = "study_program_id")
    private StudyProgram studyProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public StudyProgram getStudyProgram(){
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram){
        this.studyProgram = studyProgram;
    }
}
