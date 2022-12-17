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

    var originName: String = originName

    var fileUrl: String = fileUrl

    fun changeItemCategory(itemCategory: ItemCategory) {
        this.itemCategory = itemCategory
    }
}