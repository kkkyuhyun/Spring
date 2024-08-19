package org.scoula.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")//경로
    public String home() {//데이터
        log.info("================> HomeController /");
        return "index"; //응답 경로
        // View의 이름
    }
}