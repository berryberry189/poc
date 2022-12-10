package com.poc.domain.member.repository

import com.poc.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface MemberRepository : JpaRepository<Member, Long?> {

    fun findByEmail(email: String): Optional<Member>

    fun findByEmailAndPassword(email: String, password: String): Optional<Member>

}