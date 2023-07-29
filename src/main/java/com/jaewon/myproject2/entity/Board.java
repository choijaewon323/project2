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
@Table(name = "BOARD")
public class Board extends DateTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "WRITER")
    private String writer;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CNT")
    private Long cnt;

    @Column(name = "HEART_CNT")
    private Long heartCnt;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public Board(String title, String content, String writer, String image, Member member) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.image = image;
        this.member = member;
        this.cnt = 0L;
        this.heartCnt = 0L;
    }

    public void update(String title, String content, String writer, String image, Member member) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.image = image;
        this.member = member;
    }

    public void heartUp() {
        heartCnt++;
    }

    public void heartDown() {
        heartCnt--;
    }
}
