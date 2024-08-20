package org.scoula.test.controller;

import lombok.extern.log4j.Log4j;
import org.scoula.test.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/test")
@Log4j
public class TestController {

    @GetMapping("/basic")
    public void basic() {
        log.info("basic ====> TestController /test/basic");
    }

    @GetMapping("/user")
    public String getUser(@RequestParam("name") String name, @RequestParam("age") int age) {
        log.info("GET user ====> TestController /test/user");
        log.info("name: " + name + ", age: " + age);
        return "user"; // Assumes "user" resolves to a view like WEB-INF/views/user.jsp
    }
}

    //POST +/user + parameter (name 과 age) + user(     ) + return /user
    /*@GetMapping("/userdto")
    public void userdto(UserDTO dto) {
        log.info("GET userdto ====> TestController /test/userdto");
        log.info("dto" + dto);
    }
}*/

