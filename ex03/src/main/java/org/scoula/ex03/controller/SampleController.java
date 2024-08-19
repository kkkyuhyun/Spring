package org.scoula.ex03.controller;
import lombok.extern.log4j.Log4j;
import org.scoula.ex03.dto.SampleDTO;
import org.scoula.ex03.dto.SampleDTOList;
import org.scoula.ex03.dto.TodoDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("/sample") //공통 url 파트 handler 매핑에 대한 정보
@Log4j
public class SampleController {
    @RequestMapping("")  // url: /sample
    public void basic() {
        log.info("basic............");
    }
//RequestMapping("/sample")과 @RequestMapping(value="/basic", method) 합치면 /sample/basic

    @RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})  // url: /sample/basic
    public void basicGet() {
        log.info("basic get............");
    }

    @GetMapping("/basicOnlyGet")  // url: /sample/basicOnlyGet
    public void basicGet2() {
        log.info("basic get only get............");
    }

    @GetMapping("/ex01")
    public String ex001(SampleDTO dto) {
        log.info("" + dto); //자동으로 request scope에 저장된다.
        return "ex01";
    }

    @GetMapping("/ex02")
    public String ex02(
            @RequestParam("name") String name,
            @RequestParam("age") int age) {
        log.info("name:" + name);
        log.info("age:" + age);

        return "ex02";
    }

    @GetMapping("/ex02List")
    public String ex02List(@RequestParam("ids") String[] ids) { //RequestParam은 view로 전달이 안 된다.
        log.info("array ids:" + Arrays.toString(ids)); //array 대신에 배열 사용

        return "ex02List";
    }

    @GetMapping("/ex02Bean")
    public String ex02Bean(SampleDTOList list) {
        log.info("list dtos:" + list);

        return "ex02Bean";
    }

    @GetMapping("/ex03")
    public String ex03(TodoDTO todo) {
        log.info("todo:" + todo);
        return "ex03";
    }

    @GetMapping("/ex04")

    public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
        log.info("dto: " + dto);
        log.info("page: " + page);

        return "sample/ex04";
    }

    @GetMapping
    public void ex05() {
        log.info("/ex05........");
    }

    @GetMapping("/ex06")
    public String ex06(RedirectAttributes ra) {
        log.info("/ex06....");
        ra.addAttribute("name", "AAA");
        ra.addAttribute("age", "10");

        return "redirect:/sample/ex06-2";
    }

    @GetMapping("/ex07")
    public @ResponseBody SampleDTO ex07() {//application/json 응답 타입 json 문자열 직렬화
        log.info("ex07........");

        SampleDTO dto = new SampleDTO();
        dto.setAge(10);
        dto.setName("홍길동");

        return dto;
    }

    @GetMapping("/ex08")
    public ResponseEntity<String> ex08() {
        log.info("/ex08......");
        //{"name": "홍길동"}
        String msg = "{\"name\":\"홍길동\"}";

        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json;charset=UTF-8");

        return new ResponseEntity<>(msg, header, HttpStatus.OK);//body 헤더 상태코드
    }//API 서버 만들 때 다시 등장할 예정 ResponseEntity

    @GetMapping("/exUpload")
    public void exUpload() {//sample/exUpload
        //GET 요청이 왔을 때 void
        log.info("/exUpload..........");
    }

    @PostMapping("/exUploadPost")
    //POST 요청이 왔을 때 String redirect string
    public void exUploadPost(ArrayList<MultipartFile> files) {//Part 와 변수명이 중요
        for (MultipartFile file : files) {
            log.info("----------------------------------");
            log.info("name:" + file.getOriginalFilename());
            log.info("size:" + file.getSize());
        }
    }
}

