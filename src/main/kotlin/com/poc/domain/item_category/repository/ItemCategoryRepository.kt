package com.poc.domain.item_category.repository

import com.poc.domain.item_category.ItemCategory
import org.springframework.data.jpa.repository.JpaRepository

interface ItemCategoryRepository : JpaRepository<ItemCategory, Long?>, ItemCategoryRepositoryCustom{
}