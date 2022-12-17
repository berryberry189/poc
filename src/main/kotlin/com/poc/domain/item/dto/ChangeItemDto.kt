package com.poc.domain.item.dto

import io.swagger.annotations.ApiModelProperty

data class ChangeItemDto (

    @ApiModelProperty(value = "변경 item id 목록", required = true)
    val itemIdList: List<Long>,

    @ApiModelProperty(value = "변경 아이템 카테고리 id", required = true)
    val itemCategoryId: Long
)