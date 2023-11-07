package com.switcher.kent.web.member.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member implements Serializable {

    private static final long serialVersionUID = 1062017833925367218L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Integer memberId;
    @Column(name = "ROLE_ID", insertable = false)
    private Integer roleId;
    private String account;
    private String password;
    private String nickname;
    @Column(name = "CREATE_DATE", insertable = false)
    private Timestamp createDate;
    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;


    @PreUpdate
    public void onUpdate() {
        updateDate = new Timestamp(System.currentTimeMillis());
    }
}
