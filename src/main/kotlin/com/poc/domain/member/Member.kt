package com.poc.domain.member

import com.poc.common.entity.BaseTime
import javax.persistence.*

@Entity
@Table(name = "member")
class Member(email: String, password: String): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "email", columnDefinition = "varchar(500) comment '이메일'", nullable = false)
    var email: String = email

    @Column(name = "password", columnDefinition = "varchar(500) comment '비밀번호'", nullable = false)
    var password: String = password

}