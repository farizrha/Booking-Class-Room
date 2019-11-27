package com.iuli.bookclassroom.repository;

import com.iuli.bookclassroom.models.StudentSubject;
import com.iuli.bookclassroom.models.StudyProgram;
import com.iuli.bookclassroom.models.Subject;
import com.iuli.bookclassroom.models.SubjectStudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectStudyProgramRepository extends JpaRepository<SubjectStudyProgram, Long> {

    List<SubjectStudyProgram> findAllByStudyProgram(StudyProgram studyProgram);
}