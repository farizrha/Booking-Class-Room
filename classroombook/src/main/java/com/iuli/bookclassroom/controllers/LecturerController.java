package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Lecturer;
import com.iuli.bookclassroom.models.Student;
import com.iuli.bookclassroom.repository.LecturerRepository;
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
@RequestMapping("lecturer")
public class LecturerController {
    @Autowired
    LecturerRepository lecturerRepository;
    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Lecturer());
        }

        return "pages/lecturer/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Lecturer lecturer,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Lecturer Addition Failed", lecturer,result);
            mView.setViewName("redirect:/lecturer/add");
            return mView;
        }

        lecturerRepository.save(lecturer);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Lecturer Succesfully Enrolled",null,null);
        mView.setViewName("redirect:/lecturer");
        return mView;
    }
    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from student
        List<Lecturer> lecturerList = lecturerRepository.findAll();
        mView.addObject("lecturerList", lecturerList);
        mView.setViewName("pages/lecturer/index");
        return mView;
    }
}