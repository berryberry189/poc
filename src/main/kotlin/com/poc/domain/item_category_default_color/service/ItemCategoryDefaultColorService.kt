package com.poc.domain.item_category_default_color.service

import com.poc.domain.item_category.repository.ItemCategoryRepository
import com.poc.domain.item_category_default_color.ItemCategoryDefaultColor
import com.poc.domain.item_category_default_color.dto.ItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.dto.ListItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.dto.SaveItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.repository.ItemCategoryDefaultColorRepository
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemCategoryDefaultColorService {

    @Autowired
    private lateinit var itemCategoryDefaultColorRepository: ItemCategoryDefaultColorRepository

    @Autowired
    private lateinit var itemCategoryRepository: ItemCategoryRepository

    @Transactional(readOnly = true)
    fun getItemCategoryDefaultColorList(itemCategoryId: Long): List<ItemCategoryDefaultColorDto> {

        val itemCategoryDefaultColorList = itemCategoryDefaultColorRepository.findAll()

        return itemCategoryDefaultColorList.map {
            ItemCategoryDefaultColorDto.fromEntity(it)
        }
    }

    @Transactional(readOnly = true)
    fun getItemCategoryDefaultColorAllList(pageRequest: PageRequest): ListItemCategoryDefaultColorDto {
        val page = itemCategoryDefaultColorRepository.getItemCategoryDefaultColorList(pageRequest)
        return ListItemCategoryDefaultColorDto(page.content, page.totalElements)
    }

    @Transactional
    fun saveItemCategoryDefaultColor(saveItemCategoryDefaultColorDto: SaveItemCategoryDefaultColorDto): ItemCategoryDefaultColorDto {
        val itemCategory = itemCategoryRepository.findById(saveItemCategoryDefaultColorDto.itemCategoryId)
            .orElseThrow { throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }

        val defaultColorList =
            itemCategoryDefaultColorRepository.findAllByItemCategory_Id(saveItemCategoryDefaultColorDto.itemCategoryId)
        if(defaultColorList.size == 8) {
            throw BadRequestException(BaseResponseCode.BAD_REQUEST, "아이템 카테고리별 등록 색상 개수를 초과합니다. 삭제 후 재등록 부탁드립니다.")
        }

        val itemCategoryDefaultColorOptional = itemCategoryDefaultColorRepository.findByItemCategory_IdAndColor(
            saveItemCategoryDefaultColorDto.itemCategoryId,
            saveItemCategoryDefaultColorDto.color
        )
        if(itemCategoryDefaultColorOptional.isPresent) {
            throw BadRequestException(BaseResponseCode.BAD_REQUEST, "이미 저장된 아이템카테고리의 색상입니다.")
        }

        val savedItemCategoryDefaultColor = itemCategoryDefaultColorRepository.save(
            ItemCategoryDefaultColor(
                itemCategory,
                saveItemCategoryDefaultColorDto.color
            ))

        return ItemCategoryDefaultColorDto.fromEntity(savedItemCategoryDefaultColor)
    }

    @Transactional
    fun deleteItemCategoryDefaultColor(itemCategoryDefaultColorId: Long): Boolean {
       val itemCategoryDefaultColor =
           itemCategoryDefaultColorRepository.findById(itemCategoryDefaultColorId)
            .orElseThrow { throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }

        itemCategoryDefaultColorRepository.delete(itemCategoryDefaultColor)

        return true
    }
}