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

    @Column(name = "name", columnDefinition = "varchar(255) comment '아이템 카테고리 명'", nullable = false)
    var name: String = name

    @Column(name = "z_index", columnDefinition = "bigint comment 'z index'")
    var zIndex: Integer? = null;


}