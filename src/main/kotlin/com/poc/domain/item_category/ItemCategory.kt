package com.poc.domain.item_category

import javax.persistence.*

@Entity
@Table(name = "item_category")
class ItemCategory(parentItemCategory: ItemCategory?, name: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_item_category_id")
    var parentItemCategory: ItemCategory? = parentItemCategory

    var name: String = name

    var zIndex: Integer? = null;


}