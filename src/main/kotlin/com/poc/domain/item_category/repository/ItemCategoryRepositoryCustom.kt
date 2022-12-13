package com.poc.domain.item_category.repository

import com.poc.domain.item_category.dto.ItemCategoryProjectionDto

interface ItemCategoryRepositoryCustom {

    fun getItemCategoryList() : List<ItemCategoryProjectionDto>
}