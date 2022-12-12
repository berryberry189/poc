package com.poc.domain.item.dto

import com.poc.domain.item.enum_type.ItemCategory
import java.time.LocalDateTime

data class ItemProjectionDto (
    val itemId: Long,
    val itemCategory: ItemCategory,
    val originName: String,
    val fileUrl: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)