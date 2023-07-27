package com.jaewon.myproject2.entity;

import com.jaewon.myproject2.common.DateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@NoArgsConstructor
@Entity
@Table(name = "MEMBER")
public class Member extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "BIRTH")
    private String birth;

    @Column(name = "ROLE")
    private String role;

    public Member(String email, String password, String nickname, String gender, String birth, String role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
        this.role = role;
    }

    public void update(String password, String nickname, String gender, String birth) {
        this.password = password;
        this.nickname = nickname;
        this.gender = gender;
        this.birth = birth;
    }
}
