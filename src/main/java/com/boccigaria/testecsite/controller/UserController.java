package com.boccigaria.testecsite.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boccigaria.testecsite.repository.UserRepository;
import com.boccigaria.testecsite.repository.entity.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping(path="user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index() {
        return "redirect:/user/all";
    }

    @GetMapping("all")
    private String all(Model model) {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/user/list";
    }

    @GetMapping("add")
    private String addForm(@ModelAttribute User user) {
        return "/user/add/form";
    }

    @PostMapping("add")
    private String addConfirm(@ModelAttribute User user, Model model) {
        if (user == null) {
            model.addAttribute("error", "User is null");
            return "/user/add/form";
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/add/form";
        }
        return "redirect:/user/add/complete/" + user.getId();
    }

    @GetMapping("add/complete/{id}")
    private String addComplete(@PathVariable int id, Model model) {
        try {
            var user = userRepository.findById(id).get();
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            return "redirect:/user/all";
        }
        return "/user/add/complete";
    }

    @GetMapping("edit/{id}")
    private String editForm(@PathVariable String id, Model model) {
        var user = userRepository.findById(Integer.parseInt(id));
        if (!user.isPresent()) return "redirect:/user/all";
        model.addAttribute("user", user.get());
        return "/user/edit/form";
    }

    @PostMapping("edit/confirm/{id}")
    public String editConfirm(@PathVariable int id, @ModelAttribute User user, Model model) {
        if (user == null) {
            model.addAttribute("error", "User is null");
            return "/user/edit/form";
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/user/edit/form";
        }
        return "redirect:/user/edit/complete/" + id;
    }

    @GetMapping("edit/complete/{id}")
    private String editComplete(@PathVariable int id, Model model) {
        try {
            var user = userRepository.findById(id).get();
            model.addAttribute("user", user);
        } catch (NoSuchElementException e) {
            return "redirect:/user/all";
        }
        return "/user/edit/complete";
    }

}
