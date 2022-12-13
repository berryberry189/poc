package com.poc.domain.item.dto

data class ListItemDto (
    val contents : List<ItemProjectionDto>,
    val totalCount : Long
)