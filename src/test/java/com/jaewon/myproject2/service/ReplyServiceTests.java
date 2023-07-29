package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.BoardRequestDto;
import com.jaewon.myproject2.dto.MemberRequestDto;
import com.jaewon.myproject2.dto.ReplyRequestDto;
import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.entity.Reply;
import com.jaewon.myproject2.entity.BoardRepository;
import com.jaewon.myproject2.entity.MemberRepository;
import com.jaewon.myproject2.entity.ReplyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ReplyServiceTests {
    @Autowired
    ReplyService replyService;
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void afterEach() {
        replyRepository.deleteAll();
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void createTest() {
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setEmail("email");
        memberRequestDto.setPassword("password");

        Member member = memberRepository.save(memberRequestDto.toEntity());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");

        Board board = boardRepository.save(boardRequestDto.toEntity(member));

        // test
        ReplyRequestDto replyRequestDto = new ReplyRequestDto();
        replyRequestDto.setContent("content");
        replyRequestDto.setWriter("writer");

        replyService.create(replyRequestDto, board.getId(), member.getEmail());

        assertThat(replyRepository.findAll().get(0).getContent()).isEqualTo("content");
        assertThat(replyRepository.findAll().get(0).getBoard().getId()).isEqualTo(board.getId());
    }

    @Test
    void updateTest() {
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setEmail("email");
        memberRequestDto.setPassword("password");

        Member member = memberRepository.save(memberRequestDto.toEntity());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");

        Board board = boardRepository.save(boardRequestDto.toEntity(member));

        ReplyRequestDto replyRequestDto = new ReplyRequestDto();
        replyRequestDto.setWriter("writer");
        replyRequestDto.setContent("content");

        Reply reply = replyRepository.save(replyRequestDto.toEntity(board, member));

        // test
        replyRequestDto.setContent("content123");
        replyService.update(replyRequestDto, reply.getId());

        assertThat(replyRepository.findAll().get(0).getContent()).isEqualTo("content123");
    }

    @Test
    void deleteTest() {
        MemberRequestDto memberRequestDto = new MemberRequestDto();
        memberRequestDto.setEmail("email");
        memberRequestDto.setPassword("password");

        Member member = memberRepository.save(memberRequestDto.toEntity());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");

        Board board = boardRepository.save(boardRequestDto.toEntity(member));

        ReplyRequestDto replyRequestDto = new ReplyRequestDto();
        replyRequestDto.setWriter("writer");
        replyRequestDto.setContent("content");

        Reply reply = replyRepository.save(replyRequestDto.toEntity(board, member));

        // test
        replyService.delete(reply.getId());

        assertThat(replyRepository.findAll().size()).isEqualTo(0);
    }
}
