package com.poc.domain.member.dto

import com.poc.domain.member.Member
import java.time.LocalDateTime

data class MemberDto(
    val memberId: Long? = null,
    val email: String,
    val password: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {

    companion object {
        fun fromEntity(member: Member): MemberDto {
            return member.run {
                MemberDto(memberId = id, email = email, password = password, createdAt = createdAt, updatedAt = updatedAt)
            }
        }
    }

}