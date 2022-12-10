package com.poc.domain.member.controller

import com.poc.domain.member.dto.MemberDto
import com.poc.domain.member.dto.SaveMemberDto
import com.poc.domain.member.service.MemberService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/poc/v1/member")
@Api(value = "MemberController", tags = ["회원 관리 관련 API 입니다"])
class MemberController {

    @Autowired
    private lateinit var memberService: MemberService

    @ApiOperation(value = "회원 저장")
    @PostMapping("/")
    fun createMember(@Valid @RequestBody saveMemberDto: SaveMemberDto) : ResponseEntity<MemberDto> {
        return ResponseEntity<MemberDto>(memberService.saveMember(saveMemberDto), HttpStatus.OK)
    }

    @ApiOperation(value = "로그인")
    @GetMapping("/login")
    fun login(@RequestParam("email", required = true) email: String,
              @RequestParam("password", required = true) password: String) : ResponseEntity<MemberDto> {
        return ResponseEntity<MemberDto>(memberService.login(email, password), HttpStatus.OK)
    }


}