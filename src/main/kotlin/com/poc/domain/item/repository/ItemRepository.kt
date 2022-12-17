package com.poc.domain.item.repository

import com.poc.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying

interface ItemRepository : JpaRepository<Item, Long?>, ItemRepositoryCustom {

    @Modifying
    fun deleteByIdIn(itemIdList: List<Long>)

}