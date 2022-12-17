package com.poc.domain.item.dto

import io.swagger.annotations.ApiModelProperty

data class DeleteItemDto (

    @ApiModelProperty(value = "삭제 item id 목록", required = true)
    val itemIdList: List<Long>
)