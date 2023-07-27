package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.MemberRequestDto;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberServiceTests {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAll();
    }

    @Test
    void createTest() {
        MemberRequestDto requestDto = new MemberRequestDto();

        requestDto.setGender("man");
        requestDto.setBirth("990323");
        requestDto.setNickname("choijaewon");
        requestDto.setEmail("chjw0265@gmail.com");
        requestDto.setPassword("1234");

        // test
        memberService.create(requestDto);

        assertThat(memberRepository.count()).isEqualTo(1L);
        assertThat(memberRepository.findAll().get(0).getNickname()).isEqualTo("choijaewon");
    }

    @Test
    void updateTest() {
        MemberRequestDto requestDto = new MemberRequestDto();

        requestDto.setGender("man");
        requestDto.setBirth("990323");
        requestDto.setNickname("choijaewon");
        requestDto.setEmail("chjw0265@gmail.com");
        requestDto.setPassword("1234");

        memberRepository.save(requestDto.toEntity());

        requestDto.setPassword("4321");

        // test
        memberService.update(requestDto);

        assertThat(memberRepository.findAll().get(0).getPassword()).isEqualTo("4321");
    }

    @Test
    void deleteTest() {
        MemberRequestDto requestDto = new MemberRequestDto();

        requestDto.setGender("man");
        requestDto.setBirth("990323");
        requestDto.setNickname("choijaewon");
        requestDto.setEmail("chjw0265@gmail.com");
        requestDto.setPassword("1234");

        memberRepository.save(requestDto.toEntity());

        // test
        memberService.delete(requestDto);

        assertThat(memberRepository.findAll().size()).isEqualTo(0);
    }
}
