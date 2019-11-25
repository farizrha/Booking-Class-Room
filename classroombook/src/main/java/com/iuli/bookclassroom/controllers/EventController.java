package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.Event;
import com.iuli.bookclassroom.repository.EventRepository;
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
@RequestMapping("event")
public class EventController {


        @Autowired
        EventRepository eventRepository;

        @GetMapping("")
        public ModelAndView index(ModelAndView mView,
                                  @ModelAttribute(name = "result_code") String result_code,
                                  @ModelAttribute(name = "result_message") String result_message) {
            List<Event> eventList = eventRepository.findAll();
            mView.addObject("eventList", eventList);

            mView.setViewName("pages/event/index");
            return mView;
        }

        @GetMapping("/add")
        public String add(Model model,
                          @ModelAttribute(name ="result code") String result_code,
                          @ModelAttribute(name ="result message") String result_message) {
            if (!model.containsAttribute("data")){
                model.addAttribute("data", new Event());
            }
            return "pages/event/add";
        }

        @PostMapping("/create")
        public ModelAndView create(@Valid @ModelAttribute(name = "data") Event event,
                                   BindingResult result, ModelAndView mView,
                                   RedirectAttributes redirectAttributes) {
            if (result.hasErrors()){
                GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Error", event, result);
                mView.setViewName("redirect:/event/add");
                return mView;
            }
            eventRepository.save(event);
            GlobalMethods.setRedirectAttribute(redirectAttributes, "1", "Success", null, null);
            mView.setViewName("redirect:/event");
            return mView;
        }



    }


