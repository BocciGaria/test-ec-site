package com.boccigaria.testecsite.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.boccigaria.testecsite.repository.CourseRepository;
import com.boccigaria.testecsite.repository.OrderRepository;
import com.boccigaria.testecsite.repository.entity.Course;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private HttpSession session;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("")
    public ModelAndView index() {
        var mav = new ModelAndView("/cart/index");
        if (session.getAttribute("coursesIdsInCart") == null) {
            mav.addObject("empty", true);
            return mav;
        }
        var coursesIdsInCart = (ArrayList<String>)session.getAttribute("coursesIdsInCart");
        var courses = courseRepository.findAllById(coursesIdsInCart);
        mav.addObject("courses", courses);
        Integer totalPrice = 0;
        for (Course c : courses) {
            totalPrice += c.getTotalPrice();
        }
        mav.addObject("totalPrice", totalPrice);

        return mav;
    }

    @GetMapping("clear")
    private String clear() {
        session.removeAttribute("coursesIdsInCart");
        return "redirect:/cart";
    }

}
