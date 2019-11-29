package com.iuli.bookclassroom.controllers;

import com.iuli.bookclassroom.helper.GlobalMethods;
import com.iuli.bookclassroom.models.*;
import com.iuli.bookclassroom.repository.*;
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
@RequestMapping("book")
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    RoomRepository roomRepository;
//
    @GetMapping("")
        public String index(Model model,
                /*@PathVariable Long id,*/
                @ModelAttribute(name = "result_code") String result_code,
                @ModelAttribute(name = "result_message") String result_message) {
            if (!model.containsAttribute("data")) {
                model.addAttribute("data", new Book());
            }


            List<Event> typeeventList = eventRepository.findAll();
            model.addAttribute("typeeventList", typeeventList);


            List<Room> typeroomList = roomRepository.findAll();
            model.addAttribute("typeroomList", typeroomList);


            return "pages/book/index";

    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute(name = "data") Book book,
                               BindingResult result, ModelAndView mView,
                               RedirectAttributes redirectAttributes){
        if (result.hasErrors()) {
            GlobalMethods.setRedirectAttribute(redirectAttributes, "0", "Book Request Failed", book,result);
            mView.setViewName("redirect:/book");
            return mView;
        }

        bookRepository.save(book);
        GlobalMethods.setRedirectAttribute(redirectAttributes,"1","Book Succesfully Requested",null,null);
        mView.setViewName("redirect:/book/success");
        return mView;
    }


    @GetMapping("/success")
    public ModelAndView index(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from room
        mView.setViewName("pages/book/success");
        return mView;
    }

    @GetMapping("/list")
    public ModelAndView list(ModelAndView mView,
                              @ModelAttribute(name = "result_code") String result_code,
                              @ModelAttribute(name = "result_message") String result_message) {
        //get all data from room
        List<Book> bookList = bookRepository.findAll();
        mView.addObject("bookList", bookList);
        mView.setViewName("pages/book/list");
        return mView;
    }


}