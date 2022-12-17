package com.poc.domain.item.dto

import com.poc.domain.item.Item
import java.time.LocalDateTime

data class ItemDto (
    val itemId: Long? = null,
    val itemCategoryId: Long? = null,
    val itemCategoryName: String,
    val zIndex: Integer?,
    val parentItemCategoryId: Long? = null,
    val parentItemCategoryName: String?,
    val originName: String,
    val fileUrl: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {

    companion object {
        fun fromEntity(item: Item) : ItemDto {
            return item.run {
                ItemDto(
                    itemId = id,
                    itemCategoryId = itemCategory.id,
                    itemCategoryName = itemCategory.name,
                    zIndex = itemCategory.zIndex,
                    parentItemCategoryId = itemCategory.parentItemCategory?.id,
                    parentItemCategoryName = itemCategory.parentItemCategory?.name,
                    originName = originName,
                    fileUrl = fileUrl,
                    createdAt = createdAt,
                    updatedAt = updatedAt)
            }
        }
    }
}