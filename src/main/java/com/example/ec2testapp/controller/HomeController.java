package com.example.ec2testapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "EC2 배포 테스트 성공!");
        model.addAttribute("currentTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        model.addAttribute("serverInfo", System.getProperty("os.name") + "/ JAVA " );
        return "index";
    }
}
