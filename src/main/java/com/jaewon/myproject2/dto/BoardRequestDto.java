package com.jaewon.myproject2.dto;

import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String image;
    private Long cnt;
    private Long heartCnt;
    private LocalDateTime createdTime;

    public Board toEntity(Member member) {
        return new Board(title, content, writer, image, member);
    }
}
