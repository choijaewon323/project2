package com.jaewon.myproject2.dto;

import com.jaewon.myproject2.entity.Board;
import com.jaewon.myproject2.entity.Member;
import com.jaewon.myproject2.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReplyRequestDto {
    private String writer;
    private String content;

    public Reply toEntity(Board board, Member member) {
        return new Reply(writer, content, board, member);
    }
}
