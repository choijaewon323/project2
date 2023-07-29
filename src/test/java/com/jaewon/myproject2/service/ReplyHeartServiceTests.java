package com.jaewon.myproject2.service;

import com.jaewon.myproject2.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReplyHeartServiceTests {
    @Autowired
    ReplyHeartService replyHeartService;

    @Autowired
    ReplyRepository replyRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ReplyHeartRepository replyHeartRepository;

    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void afterEach() {
        replyHeartRepository.deleteAll();
        replyRepository.deleteAll();
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void createTest() {
        Member member = memberRepository.save(new Member("email", "password", "","","",""));
        Board board = boardRepository.save(new Board("title", "content", "", "", member));
        Reply reply = replyRepository.save(new Reply("writer", "", board, member));

        // test
        replyHeartService.create(reply.getId(), member.getEmail());

        assertThat(replyHeartRepository.findAll().get(0).getReply().getWriter()).isEqualTo("writer");
        assertThat(replyHeartRepository.findAll().get(0).getMember().getEmail()).isEqualTo("email");
    }

    @Test
    void deleteTest() {
        Member member = memberRepository.save(new Member("email", "password", "","","",""));
        Board board = boardRepository.save(new Board("title", "content", "", "", member));
        Reply reply = replyRepository.save(new Reply("writer", "", board, member));

        // test
        replyHeartService.delete(reply.getId(), member.getEmail());

        assertThat(replyHeartRepository.findAll().size()).isEqualTo(0);
    }
}
