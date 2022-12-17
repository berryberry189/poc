package com.poc.domain.item_category_default_color.repository

import com.poc.domain.item_category_default_color.dto.ItemCategoryDefaultColorDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ItemCategoryDefaultColorRepositoryCustom {

    fun getItemCategoryDefaultColorList(pageRequest: PageRequest): Page<ItemCategoryDefaultColorDto>
}