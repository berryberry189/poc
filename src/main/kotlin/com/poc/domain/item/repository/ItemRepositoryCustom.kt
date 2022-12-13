package com.poc.domain.item.repository

import com.poc.domain.item.dto.ItemProjectionDto
import com.poc.domain.item.enum_type.ItemCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ItemRepositoryCustom {

    fun getItemList(itemCategory: ItemCategory?, pageRequest: PageRequest) : Page<ItemProjectionDto>



}