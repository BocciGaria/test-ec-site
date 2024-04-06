package com.boccigaria.testecsite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping(path="user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public String index() {
        return "redirect:user/all";
    }


    @GetMapping("add")
    private String addForm(@ModelAttribute User user) {
        return "user/add/form";
    }

    @PostMapping("/add")
    private String addComplete(@ModelAttribute User user, Model model) {
        if (user == null) {
            model.addAttribute("error", "User is null");
            return "user/add/form";
        }
        try {
            userRepository.save(user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "user/add/form";
        }
        model.addAttribute("error", null);
        return "user/add/complete";
    }

    @GetMapping("all")
    private String all(Model model) {
        var users = userRepository.findAll();
        model.addAttribute("users", users);
        return "user/list";
    }

}
