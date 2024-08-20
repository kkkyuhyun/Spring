package org.scoula.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home() {
    //    log.info("==============> HomeController /");
    //    return "index";//View의 이름
        return "redirect:/board/list";
    }
}
//06.1 -> p.21 처리

