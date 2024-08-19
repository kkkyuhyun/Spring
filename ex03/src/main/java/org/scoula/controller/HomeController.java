package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home(Model model) { //Model Map 에 담아준다. HomeController 에 Model 을 요청해준다
        model.addAttribute("name", "홍길동");//request Scope에 저장

        return "index";  // /WEB-INF/views/index.jsp 파일을 반환
    }
}
