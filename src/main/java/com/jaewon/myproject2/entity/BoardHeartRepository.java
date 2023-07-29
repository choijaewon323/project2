package com.jaewon.myproject2.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardHeartRepository extends JpaRepository<BoardHeart, Long> {
    Optional<BoardHeart> deleteByBoardAndMember(Board board, Member member);
}
