package com.boccigaria.testecsite.controller;

import java.util.ArrayList;

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

        // セッションでカート内コースを保持
        ArrayList<String> coursesIdsInCart;
        if (session.getAttribute("coursesIdsInCart") == null) {
            coursesIdsInCart = new ArrayList<>();
        } else {
            coursesIdsInCart = (ArrayList<String>)session.getAttribute("coursesIdsInCart");
        }
        if (!coursesIdsInCart.contains(course_id)) {
            coursesIdsInCart.addLast(course_id);
        }
        session.removeAttribute("coursesIdsInCart");
        session.setAttribute("coursesIdsInCart", coursesIdsInCart);
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
