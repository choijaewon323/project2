package com.jaewon.myproject2.controller;

import com.jaewon.myproject2.dto.BoardRequestDto;
import com.jaewon.myproject2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/v1/api/board/{email}")
    public void createBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable String email) {
        boardService.create(boardRequestDto, email);
    }

    @PutMapping("/v1/api/board/{email}/{boardId}")
    public void updateBoard(@RequestBody BoardRequestDto boardRequestDto, @PathVariable String email, @PathVariable Long boardId) {
        boardService.update(boardRequestDto, email, boardId);
    }

    @DeleteMapping("/v1/api/board/{boardId}")
    public void deleteBoard(@PathVariable Long boardId) {
        boardService.delete(boardId);
    }
}
