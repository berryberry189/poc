package com.poc.domain.item_category.service

import com.poc.domain.item_category.dto.ItemCategoryProjectionDto
import com.poc.domain.item_category.repository.ItemCategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemCategoryService {

    @Autowired
    private lateinit var itemCategoryRepository: ItemCategoryRepository

    @Transactional(readOnly = true)
    fun getItemCategorList(): List<ItemCategoryProjectionDto> {
        return itemCategoryRepository.getItemCategoryList()
    }
}