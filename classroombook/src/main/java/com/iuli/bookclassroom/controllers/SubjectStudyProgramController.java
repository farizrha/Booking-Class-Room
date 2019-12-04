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
@RequestMapping("studyprogram/subject")
public class SubjectStudyProgramController {
    @Autowired
    SubjectStudyProgramRepository subjectStudyProgramRepository;

    @Autowired
    StudyProgramRepository studyProgramRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/add/{id}")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message,
                      @PathVariable Long id //id study program
                      ) {
        Optional<StudyProgram> studyProgramOptional = studyProgramRepository.findById(id);
        if(!studyProgramOptional.isPresent()){
            return "redirect:/";
        }
        if (!model.containsAttribute("data")) {
            StudyProgram studyProgram =  studyProgramOptional.get();
            SubjectStudyProgram subjectStudyProgram = new SubjectStudyProgram();
            subjectStudyProgram.setStudyProgram(studyProgram);

            model.addAttribute("data", subjectStudyProgram);
            model.addAttribute("studyProgram", studyProgram);
        }

        return "pages/subjectstudyprogram/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Subject subject,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Subject Addition Failed", subject,result);
            mView.setViewName("redirect:/subject/add");
            return mView;
        }

        subjectRepository.save(subject);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Subject Succesfully Enrolled",null,null);
        mView.setViewName("redirect:/subject");
        return mView;
    }
    @GetMapping("/{id}")
    public ModelAndView index(ModelAndView mView,
                              @PathVariable Long id, //Study Program id
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
        mView.setViewName("pages/subjectstudyprogram/index");
        return mView;
    }
}