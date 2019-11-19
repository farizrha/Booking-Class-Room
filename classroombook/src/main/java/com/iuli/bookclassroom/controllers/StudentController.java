package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.models.Student;
import com.iuli.bookclassroom.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        List<Student> students = studentRepository.findAll();
        mView.addObject("students", students);
        mView.setViewName("pages/student/index");
        return mView;
    }

//    @GetMapping("/add")
//    public String add( Model model,
//                       @ModelAttribute(name = "result_code") String result_code,
//                       @ModelAttribute(name = "result_message") String result_message
//                       ){
//        if(!model.containsAttribute("data")){
//            model.addAttribute("data", new Kementerian());
//        }
//        return "pages/kementerian/add";
//    }
//
//    @PostMapping("/create")
//    public ModelAndView create(@Valid @ModelAttribute(name = "data") Kementerian kementerian,
//                               BindingResult result, ModelAndView mView,
//                               RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Kementerian Gagal Ditambahkan", kementerian, result);
//            mView.setViewName("redirect:/kementerian/add");
//            return mView;
//        }
//        kementerian.setKodeAndName();
//        kementerianRepository.save(kementerian);
//        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Kementerian Berhasil Ditambahkan", null, null);
//        mView.setViewName("redirect:/kementerian");
//        return mView;
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(Model model,
//                       @PathVariable Long id,
//                       @ModelAttribute(name = "result_code") String result_code,
//                       @ModelAttribute(name = "result_message") String result_message) {
//        Optional<Kementerian> kementerian= kementerianRepository.findById(id);
//        if (!kementerian.isPresent()) {
//            return "redirect:/kementerian";
//        } else {
//            // put default data for ${object} on form is there's no default
//            if (!model.containsAttribute("data")) {
//                model.addAttribute("data", kementerian.get());
//            }
//            return "pages/kementerian/edit";
//        }
//    }
//
//    @PostMapping("/update/{id}")
//    public ModelAndView update(@PathVariable Long id,
//                               @Valid @ModelAttribute(name = "data") Kementerian kementerian,
//                               BindingResult result,
//                               ModelAndView mView,
//                               RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Kementerian Gagal Diubah", kementerian, result);
//            mView.setViewName("redirect:/kementerian/edit/" + id);
//            return mView;
//        }
//
//        kementerian.setId(id);
//        kementerian.setKodeAndName();
//        kementerianRepository.save(kementerian);
//        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Kementerian Berhasil Diubah", null, null);
//        mView.setViewName("redirect:/kementerian");
//        return mView;
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteConfirmation(Model model,
//                                     @PathVariable Long id,
//                                     @ModelAttribute(name = "result_code") String result_code,
//                                     @ModelAttribute(name = "result_message") String result_message) {
//        Optional<Kementerian> kementerian= kementerianRepository.findById(id);
//        if (!kementerian.isPresent()) {
//            return "redirect:/kementerian";
//        } else {
//            // put default data for ${object} on form is there's no default
//            if (!model.containsAttribute("data")) {
//                model.addAttribute("kementerian", kementerian.get());
//            }
//            return "pages/kementerian/delete";
//        }
//    }
//
//
//    @PostMapping("/delete")
//    public ModelAndView delete(@RequestParam("id") Long id,
//                               ModelAndView mView,
//                               RedirectAttributes redirectAttributes) {
//        Optional<Kementerian> kementerian= kementerianRepository.findById(id);
//        if (!kementerian.isPresent()) {
//            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Kementerian Gagal Dihapus", null, null);
//        } else {
//            kementerianRepository.delete(kementerian.get());
//            GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Kementerian Berhasil Dihapus", null, null);
//        }
//
//        mView.setViewName("redirect:/kementerian");
//        return mView;
//    }
}