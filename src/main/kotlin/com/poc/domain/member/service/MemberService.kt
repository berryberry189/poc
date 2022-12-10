package com.poc.domain.member.service

import com.poc.domain.member.dto.MemberDto
import com.poc.domain.member.dto.SaveMemberDto
import com.poc.domain.member.repository.MemberRepository
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class MemberService {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Transactional
    fun saveMember(saveMemberDto: SaveMemberDto) : MemberDto{
        val memberOptional = memberRepository.findByEmail(saveMemberDto.email)
        if(memberOptional.isPresent) {
            throw BadRequestException(BaseResponseCode.BAD_REQUEST, "중복된 이메일이 존재합니다.")
        }
        val member = memberRepository.save(saveMemberDto.toEntity())
        return MemberDto.fromEntity(member)
    }


    @Transactional(readOnly = true)
    fun login(email: String, password: String): MemberDto {
        val member = memberRepository.findByEmailAndPassword(email, password)
            .orElseThrow{throw BadRequestException(BaseResponseCode.BAD_REQUEST, "존재하지 않는 회원입니다.")}
        return MemberDto.fromEntity(member)
    }
}