package com.example.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // 정적 컨텐츠
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     // resources/templates/hello.html 실행 (렌더링)
    }

    @GetMapping("hello-mvc")    // MVC와 템플릿 엔진
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";    // resources/templates/hello-template.html 렌더링
    }

    @GetMapping("hello-string")     // API - 뷰 리졸버를 사용하지 않고 HTTP의 BODY에 문자 내용을 직접 반환
    @ResponseBody   // Response Body에 "hello " + name을 직접 넣음
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // 순수 문자열 응답
    }

    @GetMapping("hello-api")    // @ResponseBody를 사용하고 객체를 반환하면 객체가 JSON으로 변환됨
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // // 객체를 JSON으로 변환해 응답
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

