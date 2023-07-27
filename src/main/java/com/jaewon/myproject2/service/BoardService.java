package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.BoardRequestDto;
import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.entity.Reply;
import com.jaewon.myproject2.repository.BoardRepository;
import com.jaewon.myproject2.repository.MemberRepository;
import com.jaewon.myproject2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void create(BoardRequestDto requestDTO, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        boardRepository.save(requestDTO.toEntity(member));
    }

    @Transactional
    public void update(BoardRequestDto requestDto, String email, Long boardId) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException());

        board.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getWriter(), requestDto.getImage(), member);
    }

    @Transactional
    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException());

        List<Reply> replyList = replyRepository.findAllByBoard(board);

        for (Reply temp : replyList) {
            replyRepository.deleteById(temp.getId());
        }

        boardRepository.deleteById(boardId);
    }
}
