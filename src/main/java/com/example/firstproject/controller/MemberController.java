package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signupPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        // 1. DTO를 Entity로 변경
        Member member = form.toEntity();

        // 2. Repository를 통해 Entity를 DB에 저장
        Member saved = memberRepository.save(member);

        return "";
    }
}
