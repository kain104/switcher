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
        System.out.println("登出了");
        request.getSession().invalidate();
    }

    // 會員註冊
    @PostMapping("/registry")
    public String registry(@RequestBody Member member) {
        System.out.println(member);
        return service.registry(member);
    }

    // 更改密碼
    @PutMapping("/pwdUpdate/{id}/{oPassword}/{nPassword}")
    public String pwdUpdate(
            @PathVariable Integer id,
            @PathVariable String oPassword,
            @PathVariable String nPassword
    ) {
        System.out.println("更改中");
        // 使用接收到的參數進行處理
        // ...
        return service.updatePwdById(id, oPassword, nPassword);
    }

    // 驗證登入
    @GetMapping("/checkLogin")
    public String checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            System.out.println("成功");
            return "成功";
        } else {
            System.out.println("失敗");
            return "失敗";
        }
    }

    // 會員管理
    @GetMapping("/members")
    public List<Member> getAllMembers(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session == null){
            System.out.println("不是管理員");
        }
        System.out.println("印出所有會員");
        List<Member> members = service.getAllMembers();
        return members;
    }

    // 刪除會員
    @DeleteMapping("/memberDelete/{id}")
    public String deleteMember(@PathVariable("id") Integer id){
        System.out.println("刪除" + id);
        return service.deleteById(id);
    }

}
