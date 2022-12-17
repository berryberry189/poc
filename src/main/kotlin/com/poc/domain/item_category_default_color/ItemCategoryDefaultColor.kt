package com.poc.domain.item_category_default_color

import com.poc.domain.item_category.ItemCategory
import javax.persistence.*

@Entity
@Table(name = "item_category_default_color")
class ItemCategoryDefaultColor (itemCategory: ItemCategory, color: String){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_category_id", nullable = false)
    var itemCategory: ItemCategory = itemCategory

    @Column(name = "color", columnDefinition = "varchar(255) comment '색상'", nullable = false)
    var color: String = color

}