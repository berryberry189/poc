package com.poc.domain.item.repository

import com.poc.domain.item.Item
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<Item, Long?>, ItemRepositoryCustom {
}