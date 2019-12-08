package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.StudyProgram;
import com.iuli.bookclassroom.models.StudentSubject;
import com.iuli.bookclassroom.models.Subject;
import com.iuli.bookclassroom.models.SubjectStudyProgram;
import com.iuli.bookclassroom.repository.StudyProgramRepository;
import com.iuli.bookclassroom.repository.StudentSubjectRepository;
import com.iuli.bookclassroom.repository.SubjectRepository;
import com.iuli.bookclassroom.repository.SubjectStudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("subject/studyprogram")
public class SubjectStudyProgramController {
    @Autowired
    StudyProgramRepository studyProgramRepository;

    @Autowired
    SubjectStudyProgramRepository subjectStudyProgramRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new SubjectStudyProgram());
        }

        List<StudyProgram> typeStudyProgramList = studyProgramRepository.findAll();
        model.addAttribute("typeStudyProgramList", typeStudyProgramList);

        List<Subject> typeSubjectList = subjectRepository.findAll();
        model.addAttribute("typeSubjectList", typeSubjectList);

        return "pages/studentsubject/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") SubjectStudyProgram subjectStudyProgram,
                               BindingResult result,
                               ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Subject Addition Failed", subjectStudyProgram,result);
            mView.setViewName("redirect:/subject/studyprogram/add");
            return mView;
        }

        subjectStudyProgramRepository.save(subjectStudyProgram);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Subject Succesfully Enrolled",null,null);
        mView.setViewName("redirect:/subject/studyprogram/{id}");
        return mView;
    }
    @GetMapping("/{id}")
    public ModelAndView index(ModelAndView mView,
                              @PathVariable Long id, //Subject id
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from student
        Optional<StudyProgram> studyProgramOptional = studyProgramRepository.findById(id);
        if(!studyProgramOptional.isPresent()){
            mView.setViewName("redirect:/studyprogram");
        }
        StudyProgram studyProgram = studyProgramOptional.get();
        List<SubjectStudyProgram> subjectStudyProgramList = subjectStudyProgramRepository.findAllByStudyProgram(studyProgram);
        mView.addObject("subjectStudyProgramList", subjectStudyProgramList);
        mView.addObject("studyProgram", studyProgram);
        mView.setViewName("pages/studyprogram/index");
        return mView;
    }
}