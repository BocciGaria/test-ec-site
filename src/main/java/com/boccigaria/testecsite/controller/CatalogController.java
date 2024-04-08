package com.boccigaria.testecsite.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boccigaria.testecsite.repository.CourseRepository;
import com.boccigaria.testecsite.repository.entity.Course;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private HttpSession session;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    private String index(Model model) {
        var courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "/catalog/index";
    }

    @GetMapping("add/{course_id}")
    private String add(@PathVariable String course_id, Model model) {
        if (course_id == null) return "redirect:/catalog/index";
        Course addedCourse;
        try {
            addedCourse = courseRepository.findById(course_id).get();
            ArrayList<String> coursesInCart = new ArrayList<>();
            if (session.getAttribute("coursesInCart") != null) {
                coursesInCart.addAll(Arrays.asList((String[])session.getAttribute("coursesInCart")));
            }
            if (!coursesInCart.contains(course_id)) {
                coursesInCart.addLast(course_id);
            }
            session.removeAttribute("coursesInCart");
            session.setAttribute("coursesInCart", coursesInCart.toArray());
        } catch (NoSuchElementException e) {
            return "redirect:/catalog/index";
        }
        var courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("message", "カートに「" + addedCourse.getName() + "」を追加しました。");
        return "/catalog/index";
    }

}
