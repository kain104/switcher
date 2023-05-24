package com.switcher.kent.web.member.service;

import com.switcher.kent.web.member.entity.Member;

import java.util.List;

public interface MemberService {

    public Member login(Member member);

    public String registry(Member member);

    public String update(Member member);

    public String updatePwdById(Integer id, String oPassword, String nPassword);

    List<Member> getAllMembers();

    String deleteById(Integer id);
}
