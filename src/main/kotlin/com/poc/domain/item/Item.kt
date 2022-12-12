package com.poc.domain.item

import com.poc.common.entity.BaseTime
import com.poc.domain.item.enum_type.ItemCategory
import javax.persistence.*

@Entity
@Table(name = "item")
class Item(itemCategory: ItemCategory, originName: String, fileUrl: String): BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Enumerated(EnumType.STRING)
    var itemCategory: ItemCategory =itemCategory
    var originName: String = originName
    var fileUrl: String = fileUrl

}