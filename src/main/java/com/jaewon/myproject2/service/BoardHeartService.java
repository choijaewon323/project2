package com.jaewon.myproject2.service;

import com.jaewon.myproject2.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class BoardHeartService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final BoardHeartRepository boardHeartRepository;

    public BoardHeartService(BoardRepository boardRepository, MemberRepository memberRepository, BoardHeartRepository boardHeartRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.boardHeartRepository = boardHeartRepository;
    }

    @Transactional
    public void create(Long boardId, String email) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException());

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        boardHeartRepository.save(new BoardHeart(board, member));

        board.heartUp();
    }

    @Transactional
    public void delete(Long boardId, String email) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException());

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        boardHeartRepository.deleteByBoardAndMember(board, member);

        board.heartDown();
    }
}
