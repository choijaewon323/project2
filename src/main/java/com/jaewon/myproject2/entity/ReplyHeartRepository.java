package com.jaewon.myproject2.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyHeartRepository extends JpaRepository<ReplyHeart, Long> {
    Optional<ReplyHeart> deleteByReplyAndMember(Reply reply, Member member);
}
