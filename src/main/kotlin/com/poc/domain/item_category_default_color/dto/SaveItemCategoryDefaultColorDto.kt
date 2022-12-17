package com.poc.domain.item_category_default_color.dto

import io.swagger.annotations.ApiModelProperty

class SaveItemCategoryDefaultColorDto (

    @ApiModelProperty(value = "아이템 카테고리 id", required = true)
    val itemCategoryId: Long,

    @ApiModelProperty(value = "색상", required = true)
    val color: String
)