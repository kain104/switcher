package com.switcher.kent.web.member.dao;

import com.switcher.kent.web.member.entity.Member;

import java.util.List;

public interface MemberDao {
    Member selectForLogin(String username, String password);

    Member selectByUsername(String username);

    int insert(Member member);

    Member selectById(Integer id);


    List<Member> selectAll();

    String deleteById(Integer id);
}
