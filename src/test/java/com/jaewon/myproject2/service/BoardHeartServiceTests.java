package com.jaewon.myproject2.service;

import com.jaewon.myproject2.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardHeartServiceTests {
    @Autowired
    BoardHeartService boardHeartService;

    @Autowired
    BoardHeartRepository boardHeartRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        boardHeartRepository.deleteAll();
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void createTest() {
        Member member = memberRepository.save(new Member("email", "password", "", "", "", ""));
        Board board = boardRepository.save(new Board("title", "content", "", "", member));

        // test
        boardHeartService.create(board.getId(), member.getEmail());

        assertThat(boardHeartRepository.findAll().get(0).getBoard().getTitle()).isEqualTo("title");
        assertThat(boardHeartRepository.findAll().get(0).getMember().getEmail()).isEqualTo("email");
    }

    @Test
    void deleteTest() {
        Member member = memberRepository.save(new Member("email", "password", "", "", "", ""));
        Board board = boardRepository.save(new Board("title", "content", "", "", member));

        // test
        boardHeartService.delete(board.getId(), member.getEmail());

        assertThat(boardHeartRepository.findAll().size()).isEqualTo(0);
    }
}
