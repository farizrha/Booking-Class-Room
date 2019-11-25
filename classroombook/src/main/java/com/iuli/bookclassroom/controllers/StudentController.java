package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Student;
import com.iuli.bookclassroom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new Student());
        }

        return "pages/student/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Student student,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Student Addition Failed", student,result);
            mView.setViewName("redirect:/student/add");
            return mView;
        }

        studentRepository.save(student);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Student Succesfully Enrolled",null,null);
        mView.setViewName("redirect:/student");
        return mView;
    }
    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from student
        List<Student> studentList = studentRepository.findAll();
        mView.addObject("studentList", studentList);
        mView.setViewName("pages/student/index");
        return mView;
    }
}