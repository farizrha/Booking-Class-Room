package com.iuli.bookclassroom.controllers;


import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.StudyProgram;
import com.iuli.bookclassroom.repository.StudyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("studyprogram")
public class StudyProgramController {
    @Autowired
    StudyProgramRepository studyProgramRepository;

    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from studyprogram
        List<StudyProgram> studyProgramList = studyProgramRepository.findAll();
        mView.addObject("studyProgramList", studyProgramList);
        mView.setViewName("pages/studyprogram/index");
        return mView;
    }

    @GetMapping("add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message
                     ){
        if(!model.containsAttribute( "data")){
            model.addAttribute("data", new StudyProgram());
        }
        return "pages/studyprogram/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") StudyProgram studyProgram,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Study Program Addition is failed", studyProgram, result);
            mView.setViewName("redirect:/studyprogram/add");
            return mView;
        }

        studyProgramRepository.save(studyProgram);
        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Study Program Addition is successful", null, null);
        mView.setViewName("redirect:/studyprogram");
        return mView;
    }
}
