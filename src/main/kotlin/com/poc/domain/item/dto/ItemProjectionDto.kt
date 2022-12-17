package com.poc.domain.item.dto

import java.time.LocalDateTime

data class ItemProjectionDto (
    val itemId: Long,
    val itemCategoryId: Long? = null,
    val itemCategoryName: String,
    val zIndex: Integer?,
    val parentItemCategoryId: Long? = null,
    val parentItemCategoryName: String?,
    val originName: String,
    val fileUrl: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)