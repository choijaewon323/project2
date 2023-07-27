package com.jaewon.myproject2.repository;

import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByMember(Member member);
}
