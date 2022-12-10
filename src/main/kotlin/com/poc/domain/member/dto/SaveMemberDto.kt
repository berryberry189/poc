package com.poc.domain.member.dto

import com.poc.domain.member.Member
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SaveMemberDto(
    @field:NotEmpty
    @field:Email(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$", message = "올바른 email 형식이 아닙니다.")
    @ApiModelProperty(value = "이메일", required = true)
    val email: String,

    @field:NotEmpty
    @ApiModelProperty(value = "비밀번호", required = true)
    val password: String
) {

    fun toEntity(): Member {
        return Member(email, password)
    }

}