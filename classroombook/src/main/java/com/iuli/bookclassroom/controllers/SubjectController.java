package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Subject;
import com.iuli.bookclassroom.repository.SubjectRepository;
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
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        List<Subject> subjectList = subjectRepository.findAll();
        mView.addObject("subjectList", subjectList);

        mView.setViewName("pages/subject/index");
        return mView;
    }

    @GetMapping("/add")
    public String add(Model model,
                      @ModelAttribute(name ="result code") String result_code,
                      @ModelAttribute(name ="result message") String result_message) {
        if (!model.containsAttribute("data")){
            model.addAttribute("data", new Subject());
        }
        return "pages/subject/add";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Subject subject,
                         BindingResult result, ModelAndView mView,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()){
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Error", subject, result);
            mView.setViewName("redirect:/subject/add");
            return mView;
        }
        subjectRepository.save(subject);
        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Success", null, null);
        mView.setViewName("redirect:/subject");
        return mView;
    }



}

