package com.poc.domain.item_category.dto

data class ItemCategoryProjectionDto (
    val itemCategoryId: Long,
    val name: String,
    val parentItemCategoryId: Long?,
    val parentName: String?,
    val zIndex: Integer?
)