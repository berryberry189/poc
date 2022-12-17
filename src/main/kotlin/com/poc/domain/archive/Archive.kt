package com.poc.domain.archive

import com.poc.common.entity.BaseTime
import javax.persistence.*

@Entity
@Table(name = "archive")
class Archive (originName: String, fileUrl: String, name: String, team: String, position: String): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "origin_name", columnDefinition = "varchar(500) comment '이미지 원본 파일명'")
    var originName: String = originName

    @Column(name = "file_url", columnDefinition = "varchar(1000) comment '이미지 파일 url'")
    var fileUrl: String = fileUrl

    @Column(name = "name", columnDefinition = "varchar(100) comment '이름'")
    var name: String = name

    @Column(name = "team", columnDefinition = "varchar(100) comment '소속팀'")
    var team: String = team

    @Column(name = "position", columnDefinition = "varchar(100) comment '직무'")
    var position: String = position

}