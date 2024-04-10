package com.boccigaria.testecsite.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boccigaria.testecsite.repository.CourseRepository;
import com.boccigaria.testecsite.repository.PaymentRepository;
import com.boccigaria.testecsite.repository.entity.Order;
import com.boccigaria.testecsite.service.OrderPlacementService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private HttpSession session;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private OrderPlacementService orderPlacementService;

    @GetMapping("")
    private String form(@ModelAttribute Order order, Model model) {
        var courseIds = (ArrayList<String>)session.getAttribute("coursesIdsInCart");
        model.addAttribute("courses", courseRepository.findAllById(courseIds));
        model.addAttribute("paymentMethods", paymentRepository.findAll());

        return "/order/form";
    }

    @PostMapping("")
    private String orderPlacement(@ModelAttribute Order order, Model model) {
        try {
            orderPlacementService.placeOrder(order, (ArrayList<String>)session.getAttribute("coursesIdsInCart"));
            session.removeAttribute("coursesIdsInCart");
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/order/form";
        }
        return "/order/complete";
    }

}
