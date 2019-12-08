package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Student;
import com.iuli.bookclassroom.models.StudentSubject;
import com.iuli.bookclassroom.models.Subject;
import com.iuli.bookclassroom.repository.StudentRepository;
import com.iuli.bookclassroom.repository.StudentSubjectRepository;
import com.iuli.bookclassroom.repository.SubjectRepository;
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
@RequestMapping("student/subject")
public class StudentSubjectController {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    StudentSubjectRepository studentSubjectRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new StudentSubject());
        }

        List<Student> typestudentList = studentRepository.findAll();
        model.addAttribute("typestudentList", typestudentList);

        List<Subject> typesubjectList = subjectRepository.findAll();
        model.addAttribute("typesubjectList", typesubjectList);

        return "pages/studentsubject/add";
    }
    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") StudentSubject studentSubject,
                               BindingResult result,
                               ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Student Addition Failed", studentSubject,result);
            mView.setViewName("redirect:/student/subject/add");
            return mView;
        }

        studentSubjectRepository.save(studentSubject);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Student Succesfully Enrolled",null,null);
        mView.setViewName("redirect:/subject");
        return mView;
    }
    @GetMapping("/{id}")
    public ModelAndView index(ModelAndView mView,
                              @PathVariable Long id, //Subject id
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from student
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if(!subjectOptional.isPresent()){
            mView.setViewName("redirect:/subject");
        }
        Subject subject = subjectOptional.get();
        List<StudentSubject> studentSubjectList = studentSubjectRepository.findAllBySubject(subject);
        mView.addObject("studentSubjectList", studentSubjectList);
        mView.addObject("subject", subject);
        mView.setViewName("pages/studentsubject/index");
        return mView;
    }
}