package com.iuli.bookclassroom.helper;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class GlobalMethods {
    public static void setRedirectAttribute(RedirectAttributes redirectAttributes, String status, String message, Object data, BindingResult result) {
        redirectAttributes.addFlashAttribute("result_code", status);
        redirectAttributes.addFlashAttribute("result_message", message);
        if (data != null) {
            redirectAttributes.addFlashAttribute("data", data);
        }
        if (result != null) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.data", result);
        }
    }
}