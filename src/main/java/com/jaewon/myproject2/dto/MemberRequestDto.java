package com.jaewon.myproject2.dto;

import com.jaewon.myproject2.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String gender;
    private String birth;
    private String role;

    public Member toEntity() {
        return new Member(email, password, nickname, gender, birth, role);
    }
}
