package com.switcher.kent.web.member.service.impl;


import com.switcher.kent.web.member.dao.MemberDao;
import com.switcher.kent.web.member.entity.Member;
import com.switcher.kent.web.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao dao;


    @Override
    public Member login(Member member) {
        return dao.selectForLogin(member.getAccount(), member.getPassword());
    }

    @Transactional
    @Override
    public String registry(Member member) {
        if (dao.selectByAccount(member.getAccount()) != null){
            return "帳號重複，註冊失敗";
        }

        long timeStamp = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(new Date(timeStamp));

        member.setUpdateDate(Timestamp.valueOf(formattedDate));

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
    public String updatePwdByMemberId(Integer memberId, String oPassword, String nPassword) {
        Member member = new Member();
        member = dao.selectByMemberId(memberId);

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
    public String deleteByMemberId(Integer memberId) {
        return dao.deleteByMemberId(memberId);
    }

    @Transactional
    @Override
    public String updateNicknameByMemberId(Integer memberId, String nickname) {
        Member member = new Member();
        member = dao.selectByMemberId(memberId);

        member.setNickname(nickname);

        return "更改成功";

    }

    @Override
    public Member selectMemberByAccount(String account) {
        Member member = new Member();
        member = dao.selectMemberByAccount(account);
        return member;
    }
}
