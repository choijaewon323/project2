package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.BoardRequestDto;
import com.jaewon.myproject2.dto.MemberRequestDto;
import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.repository.BoardRepository;
import com.jaewon.myproject2.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BoardServiceTests {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @AfterEach
    void afterEach() {
        boardRepository.deleteAll();
        memberRepository.deleteAll();
    }

    @Test
    void createTest() {
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail("email");
        requestDto.setPassword("password");

        memberRepository.save(requestDto.toEntity());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");
        boardRequestDto.setContent("content");

        // test
        boardService.create(boardRequestDto, "email");

        assertThat(boardRepository.findAll().size()).isEqualTo(1);
        assertThat(boardRepository.findAll().get(0).getTitle()).isEqualTo("title");
        assertThat(boardRepository.findAll().get(0).getMember().getEmail()).isEqualTo("email");
    }

    @Test
    void updateTest() {
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail("email");
        requestDto.setPassword("password");

        memberRepository.save(requestDto.toEntity());
        Member member = memberRepository.findByEmail("email")
                .orElseThrow(() -> new NoSuchElementException());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");
        boardRequestDto.setContent("content");

        Board board = boardRepository.save(boardRequestDto.toEntity(member));

        // test
        boardRequestDto.setTitle("title1");
        boardRequestDto.setContent("content1");

        boardService.update(boardRequestDto, "email", board.getId());

        assertThat(boardRepository.findAll().get(0).getTitle()).isEqualTo("title1");
    }

    @Test
    void deleteTest() {
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail("email");
        requestDto.setPassword("password");

        memberRepository.save(requestDto.toEntity());
        Member member = memberRepository.findByEmail("email")
                .orElseThrow(() -> new NoSuchElementException());

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("title");
        boardRequestDto.setContent("content");

        Board board = boardRepository.save(boardRequestDto.toEntity(member));

        // test
        boardService.delete(board.getId());

        assertThat(boardRepository.findAll().size()).isEqualTo(0);
    }
}
