package com.switcher.kent.web.member.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member {

    private static final long serialVersionUID = 1062017833925367218L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    @Column(name = "ROLE_ID")
    private Integer roleId;
    @Column(name = "CREATE_DATE", insertable = false)
    private Timestamp createDate;
}
