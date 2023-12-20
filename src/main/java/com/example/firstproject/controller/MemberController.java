package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signupPage(){
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm form){
        log.info(form.toString());

        // 1. DTO를 Entity로 변경
        Member member = form.toEntity();
        log.info(member.toString());

        // 2. Repository를 통해 Entity를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());

        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member member = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", member);

        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        Iterable<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);

        model.addAttribute("member", memberEntity);

        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        // 1. DTO를 Entity로 변환
        Member memberEntity = form.toEntity();

        // 2. Entity DB에 저장
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);

        if(target != null)
            memberRepository.save(memberEntity);

        // 3. 수정 결과 페이지로 리다이렉트
        return "redirect:/members/" + memberEntity.getId();
    }
}
