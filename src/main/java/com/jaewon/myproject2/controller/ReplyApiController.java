package com.jaewon.myproject2.controller;

import com.jaewon.myproject2.dto.ReplyRequestDto;
import com.jaewon.myproject2.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ReplyApiController {
    private final ReplyService replyService;

    @PostMapping("/v1/api/reply/{email}/{boardId}")
    public void createReply(@RequestBody ReplyRequestDto replyRequestDto, @PathVariable String email, @PathVariable Long boardId) {
        replyService.create(replyRequestDto, boardId, email);
    }

    @PutMapping("/v1/api/reply/{replyId}")
    public void updateReply(@RequestBody ReplyRequestDto replyRequestDto, @PathVariable Long replyId) {
        replyService.update(replyRequestDto, replyId);
    }

    @DeleteMapping("/v1/api/reply/{replyId}")
    public void deleteReply(@PathVariable Long replyId) {
        replyService.delete(replyId);
    }
}
