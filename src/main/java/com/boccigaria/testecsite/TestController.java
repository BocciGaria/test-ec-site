package com.boccigaria.testecsite;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        return encoder.encode("password");
    }

}
