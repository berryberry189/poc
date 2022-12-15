package com.poc.domain.item.repository

import com.poc.domain.item.dto.ItemProjectionDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ItemRepositoryCustom {

    fun getItemList(itemCategoryId: Long?, pageRequest: PageRequest) : Page<ItemProjectionDto>



}