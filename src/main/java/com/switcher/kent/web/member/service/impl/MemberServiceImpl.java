package com.switcher.kent.web.member.service.impl;


import com.switcher.kent.web.member.dao.MemberDao;
import com.switcher.kent.web.member.entity.Member;
import com.switcher.kent.web.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;


    @Override
    public Member login(Member member) {
        return dao.selectForLogin(member.getUsername(), member.getPassword());
    }

    @Transactional
    @Override
    public String registry(Member member) {
        if (dao.selectByUsername(member.getUsername()) != null){
            return "帳號重複，註冊失敗";
        }

        member.setRoleId(2);
        dao.insert(member);
        return "註冊成功";

    }

    @Transactional
    @Override
    public String update(Member member) {
        return null;
    }

    @Transactional
    @Override
    public String updatePwdById(Integer id, String oPassword, String nPassword) {
        Member member = new Member();
        member = dao.selectById(id);

        if (!member.getPassword().equals(oPassword)){
            System.out.println(member.getPassword() + "===" + oPassword);
            return "密碼錯誤";
        }else {
            member.setPassword(nPassword);
            return "更改完成";
        }
    }

    @Override
    public List<Member> getAllMembers() {
        return dao.selectAll();
    }

    @Transactional
    @Override
    public String deleteById(Integer id) {
        return dao.deleteById(id);
    }
}
