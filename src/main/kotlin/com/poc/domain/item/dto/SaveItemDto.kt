package com.poc.domain.item.dto

import com.poc.domain.item.enum_type.ItemCategory
import com.sun.istack.NotNull
import io.swagger.annotations.ApiModelProperty

data class SaveItemDto (
    @field:NotNull
    @ApiModelProperty(value = "카테고리", required = true)
    val itemCategory: ItemCategory
)