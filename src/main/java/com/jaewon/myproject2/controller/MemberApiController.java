package com.jaewon.myproject2.controller;

import com.jaewon.myproject2.dto.MemberRequestDto;
import com.jaewon.myproject2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/v1/api/member")
    public void createMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.create(memberRequestDto);
    }

    @PutMapping("/v1/api/member")
    public void updateMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.update(memberRequestDto);
    }

    @DeleteMapping("/v1/api/member")
    public void deleteMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.delete(memberRequestDto);
    }
}
