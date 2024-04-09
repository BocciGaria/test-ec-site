package com.boccigaria.testecsite.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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

        // セッションでカート内コースを保持
        ArrayList<String> coursesInCart;
        if (session.getAttribute("coursesInCart") == null) {
            coursesInCart = new ArrayList<>();
        } else {
            coursesInCart = (ArrayList<String>)session.getAttribute("coursesInCart");
        }
        if (!coursesInCart.contains(course_id)) {
            coursesInCart.addLast(course_id);
        }
        session.removeAttribute("coursesInCart");
        session.setAttribute("coursesInCart", coursesInCart);
        // 一覧表示用
        var courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        // 追加したコース表示用
        Course addedCourse = courseRepository.findById(course_id).get();
        model.addAttribute("message", "カートに「" + addedCourse.getName() + "」を追加しました。");

        return "/catalog/index";
    }

    @GetMapping("detail/{course_id}")
    private String detail(@PathVariable String course_id, Model model) {
        if (course_id == null) return "redirect:/catalog/index";

        Course course = courseRepository.findById(course_id).get();
        model.addAttribute("course", course);
        model.addAttribute("totalPrice", course.getTotalPrice());

        return "/catalog/detail";
    }

}
