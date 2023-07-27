package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.ReplyRequestDto;
import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.entity.Reply;
import com.jaewon.myproject2.repository.BoardRepository;
import com.jaewon.myproject2.repository.MemberRepository;
import com.jaewon.myproject2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void create(ReplyRequestDto requestDto, Long boardId, String email) {
        Member member = memberRepository.findByEmail(email)
                        .orElseThrow(() -> new NoSuchElementException());

        Board board = boardRepository.findById(boardId)
                        .orElseThrow(() -> new NoSuchElementException());

        replyRepository.save(requestDto.toEntity(board, member));
    }

    @Transactional
    public void update(ReplyRequestDto requestDto, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchElementException());

        reply.update(requestDto.getWriter(), requestDto.getContent());
    }

    @Transactional
    public void delete(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}
