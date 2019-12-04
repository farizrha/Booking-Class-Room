package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Room;
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

    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new StudyProgram());
        }

        return "pages/studyprogram/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") StudyProgram studyprogram,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "StudyProgram Addition Failed", studyprogram,result);
            mView.setViewName("redirect:/room/add");
            return mView;
        }

        studyProgramRepository.save(studyprogram);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","StudyProgram Succesfully Added",null,null);
        mView.setViewName("redirect:/studyprogram");
        return mView;
    }
    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from room
        List<StudyProgram> studyprogramList = studyProgramRepository.findAll();
        mView.addObject("studyprogramList", studyprogramList);
        mView.setViewName("pages/studyprogram/index");
        return mView;
    }
    @GetMapping("/edit")
    public String edit(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new StudyProgram());
        }

        return "pages/room/edit";
    }

}
