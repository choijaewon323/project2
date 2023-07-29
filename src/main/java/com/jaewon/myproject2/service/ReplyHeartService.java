package com.jaewon.myproject2.service;

import com.jaewon.myproject2.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ReplyHeartService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;
    private final ReplyHeartRepository replyHeartRepository;

    public ReplyHeartService(ReplyRepository replyRepository, MemberRepository memberRepository, ReplyHeartRepository replyHeartRepository) {
        this.replyRepository = replyRepository;
        this.memberRepository = memberRepository;
        this.replyHeartRepository = replyHeartRepository;
    }

    @Transactional
    public void create(Long replyId, String email) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchElementException());

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        replyHeartRepository.save(new ReplyHeart(reply, member));

        reply.heartUp();
    }

    @Transactional
    public void delete(Long replyId, String email) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(() -> new NoSuchElementException());

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException());

        replyHeartRepository.deleteByReplyAndMember(reply, member);

        reply.heartDown();
    }
}
