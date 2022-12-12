package com.poc.domain.item.dto

import com.poc.domain.item.Item


data class ListItemDto (
    val contents : List<Item>,
    val totalCount : Long
)