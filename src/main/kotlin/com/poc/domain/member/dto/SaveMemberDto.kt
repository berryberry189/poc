package com.poc.domain.member.dto

import com.poc.domain.member.Member
import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SaveMemberDto(
    @field:NotEmpty
    @field:Email(message = "올바른 email 형식이 아닙니다.")
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