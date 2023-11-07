package com.switcher.kent.web.member.dao.impl;


import com.switcher.kent.web.member.dao.MemberDao;
import com.switcher.kent.web.member.entity.Member;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private Session session;

    @Override
    public Member selectForLogin(String account, String password) {
        final String sql = "select * from MEMBER where ACCOUNT = :account and PASSWORD = :password";
        return session.createNativeQuery(sql, Member.class).setParameter("account", account)
                .setParameter("password", password).uniqueResult();
    }

    @Override
    public Member selectByAccount(String account) {
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Member> cQuery = cBuilder.createQuery(Member.class);
        Root<Member> root = cQuery.from(Member.class);
        cQuery.where(cBuilder.equal(root.get("account"), account));
        return session.createQuery(cQuery).uniqueResult();
    }

    @Override
    public int insert(Member member) {
        session.persist(member);
        return 1;
    }

    @Override
    public Member selectByMemberId(Integer memberId) {
        return session.get(Member.class, memberId);
    }

    @Override
    public List<Member> selectAll() {
        final String hql = "FROM Member ORDER BY id";
        return session
                .createQuery(hql, Member.class)
                .getResultList();
    }

    @Override
    public String deleteByMemberId(Integer memberId) {
        Member member = session.load(Member.class, memberId);
        session.remove(member);
        return "成功";
    }


}
