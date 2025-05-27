package com.example.spring.controller;

import com.example.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링이 이 생성자를 통해 MemberService 객체를 스프링 컨테이너에서 찾아서 자동으로 주입 (의존성 주입, DI)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
