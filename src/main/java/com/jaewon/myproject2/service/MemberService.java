package com.jaewon.myproject2.service;

import com.jaewon.myproject2.dto.MemberRequestDto;
import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.entity.Reply;
import com.jaewon.myproject2.entity.BoardRepository;
import com.jaewon.myproject2.entity.MemberRepository;
import com.jaewon.myproject2.entity.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void create(MemberRequestDto requestDto) {
        memberRepository.save(requestDto.toEntity());
    }

    @Transactional
    public void update(MemberRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new NoSuchElementException());

        member.update(requestDto.getPassword(), requestDto.getNickname(), requestDto.getGender(), requestDto.getBirth());
    }

    @Transactional
    public void delete(MemberRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new NoSuchElementException());

        List<Board> boardList = boardRepository.findAllByMember(member);
        List<Reply> replyList = replyRepository.findAllByMember(member);

        for (Board temp : boardList) {
            boardRepository.delete(temp);
        }

        for (Reply temp : replyList) {
            replyRepository.delete(temp);
        }

        memberRepository.delete(member);
    }

}
