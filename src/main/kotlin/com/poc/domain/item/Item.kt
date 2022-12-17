package com.poc.domain.item

import com.poc.common.entity.BaseTime
import com.poc.domain.item_category.ItemCategory
import javax.persistence.*

@Entity
@Table(name = "item")
class Item(itemCategory: ItemCategory, originName: String, fileUrl: String): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id", nullable = false)
    var itemCategory: ItemCategory = itemCategory

    @Column(name = "origin_name", columnDefinition = "varchar(500) comment '이미지 원본 파일명'")
    var originName: String = originName

    @Column(name = "file_url", columnDefinition = "varchar(1000) comment '이미지 파일 url'")
    var fileUrl: String = fileUrl

    fun changeItemCategory(itemCategory: ItemCategory) {
        this.itemCategory = itemCategory
    }
}