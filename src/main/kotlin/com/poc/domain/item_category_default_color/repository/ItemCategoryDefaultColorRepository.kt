package com.poc.domain.item_category_default_color.repository

import com.poc.domain.item_category_default_color.ItemCategoryDefaultColor
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ItemCategoryDefaultColorRepository : JpaRepository<ItemCategoryDefaultColor, Long?>, ItemCategoryDefaultColorRepositoryCustom {

    fun findAllByItemCategory_Id(itemCategoryId: Long): List<ItemCategoryDefaultColor>
    fun findByItemCategory_IdAndColor(itemCategoryId: Long, color: String): Optional<ItemCategoryDefaultColor>
}