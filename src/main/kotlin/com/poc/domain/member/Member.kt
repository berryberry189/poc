package com.poc.domain.member

import com.poc.common.entity.BaseTime
import javax.persistence.*

@Entity
@Table(name = "member")
class Member(email: String, password: String): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var email: String = email
    var password: String = password

}