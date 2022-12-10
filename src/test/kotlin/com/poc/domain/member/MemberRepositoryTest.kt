package com.poc.domain.member

import com.poc.domain.member.repository.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.transaction.Transactional

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun saveMemberTest() {
        // given
        val email = "user@email.com"
        val password = "password"
        val member = Member(email, password)

        // when
        memberRepository.save(member)
        val memberList = memberRepository.findAll()

        // then
        Assertions.assertNotNull(memberList)
        Assertions.assertEquals("user@email.com", memberList.get(0).email)
    }

    @DisplayName("findByEmail 테스트")
    @Test
    fun findByEmailTest() {
        // given
        val email = "user@email.com"
        val password = "password"
        val member = Member(email, password)

        // when
        memberRepository.save(member)
        val findMember = memberRepository.findByEmail(email).get()

        // then
        Assertions.assertNotNull(findMember)
        Assertions.assertEquals("user@email.com", findMember.email)
    }

}