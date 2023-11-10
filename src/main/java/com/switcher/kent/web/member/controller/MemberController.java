package com.switcher.kent.web.member.controller;

import com.switcher.kent.web.member.entity.Member;
import com.switcher.kent.web.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService service;

    // 登入
    @PostMapping("/login")
    public Member login(HttpServletRequest request, @RequestBody Member member) {
        member = service.login(member);
        if (member != null) {
            HttpSession session = request.getSession();
            System.out.println(session.getId());
        }
        return member;
    }

    // 登出
    @GetMapping("/signOut")
    public void signOut(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    // 會員註冊
    @PostMapping("/registry")
    public String registry(@RequestBody Member member) {
        return service.registry(member);
    }

    // 更改密碼
    @PutMapping("/pwdUpdate/{memberId}/{oPassword}/{nPassword}")
    public String pwdUpdate(
            @PathVariable Integer memberId,
            @PathVariable String oPassword,
            @PathVariable String nPassword
    ) {
        // 使用接收到的參數進行處理
        return service.updatePwdByMemberId(memberId, oPassword, nPassword);
    }

    // 驗證登入
    @GetMapping("/checkLogin")
    public String checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return "成功";
        } else {
            return "失敗";
        }
    }

    // 會員管理
    @GetMapping("/members")
    public List<Member> getAllMembers(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            System.out.println("不是管理員");
        }
        List<Member> members = service.getAllMembers();
        return members;
    }

    @PutMapping("/updateMember/{memberId}/{nickname}")
    public String updateMember(
            @PathVariable("memberId") Integer memberId,
            @PathVariable("nickname") String nickname
    ) {
        return service.updateNicknameByMemberId(memberId, nickname);
    }


    // 刪除會員
    @DeleteMapping("/deleteMember/{memberId}")
    public String deleteMember(@PathVariable("memberId") Integer memberId) {
        return service.deleteByMemberId(memberId);
    }

}
