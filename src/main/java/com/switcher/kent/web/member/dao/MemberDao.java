package com.switcher.kent.web.member.dao;

import com.switcher.kent.web.member.entity.Member;

import java.util.List;

public interface MemberDao {
    Member selectForLogin(String account, String password);

    Member selectByAccount(String account);

    int insert(Member member);

    Member selectByMemberId(Integer memberId);


    List<Member> selectAll();

    String deleteByMemberId(Integer memberId);

}
