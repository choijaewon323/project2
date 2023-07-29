package com.jaewon.myproject2.entity;

import com.jaewon.myproject2.common.DateTime;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.time.LocalDateTime;


@SuperBuilder
@Getter
@NoArgsConstructor
@Entity
@Table(name = "REPLY")
public class Reply extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_ID")
    private Long id;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "HEART_CNT")
    private Long heartCnt;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Reply(String writer, String content, Board board, Member member) {
        this.writer = writer;
        this.content = content;
        this.board = board;
        this.member = member;
        this.heartCnt = 0L;
    }

    public void update(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }

    public void heartUp() {
        heartCnt++;
    }

    public void heartDown() {
        heartCnt--;
    }
}
