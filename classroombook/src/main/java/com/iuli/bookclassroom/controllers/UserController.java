package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.User;
import com.iuli.bookclassroom.repository.RoleRepository;
import com.iuli.bookclassroom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder pEncoder;

    /*
    User list page
     */
    @GetMapping("/")
    public ModelAndView uakpb(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        mView.setViewName("pages/user/uakpb");
        return mView;
    }

    @GetMapping("/add/{role}")
    public String add(Model model,
                      @PathVariable Long role,
                      @ModelAttribute(name = "result_code") String result_code,
                      @ModelAttribute(name = "result_message") String result_message) {
        // put default data for ${object} on form is there's no default
        if (!model.containsAttribute("data")) {
            model.addAttribute("data", new User());
        }
        return "pages/user/addpb";
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") User user, BindingResult result, ModelAndView mView, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Failed to add entry for user", user, result);
            mView.setViewName("redirect:/user/add");
            return mView;
        }

        User isUsernameExisted = userRepository.findByUsername(user.getUsername());
        if(isUsernameExisted != null){
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Succeed to add entry for user.", user, result);
            mView.setViewName("redirect:/user/add");
            return mView;
        }

        // ENCRYPT PASSWORD
        user.setPassword(pEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Succeed to add entry for user", null, null);
        mView.setViewName("redirect:/user");
        return mView;
    }
    @GetMapping("/edit/{id}")
    public String edit(Model model,
                       @PathVariable Long id,
                       @ModelAttribute(name = "result_code") String result_code,
                       @ModelAttribute(name = "result_message") String result_message) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return "redirect:/user";
        } else {
            // put default data for ${object} on form is there's no default
            if (!model.containsAttribute("data")) {
                model.addAttribute("data", user.get());
            }
            return "pages/user/edit";
        }
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable Long id,
                               @Valid @ModelAttribute(name = "data") User user,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Failed to edit entry for user", user, result);
            mView.setViewName("redirect:/user/pb/edit/" + id);
            return mView;
        }

        user.setId(id);
        // ENCRYPT PASSWORD
        if (user.getPassword().equals("")) {
            user.setPassword(userRepository.findById(id).orElseGet(User::new).getPassword());
        } else {
            user.setPassword(pEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
        GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Succeed to edit entry for user", null, null);
        mView.setViewName("redirect:/user");
        return mView;
    }
}