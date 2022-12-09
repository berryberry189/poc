package com.poc.domain.member

import javax.persistence.*

@Entity
@Table(name = "member")
class Member(name: String, email: String, password: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var name: String = name
    var email: String = email
    var password: String = password

}