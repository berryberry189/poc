package com.poc.domain.item.service

import com.poc.common.service.S3Service
import com.poc.domain.item.Item
import com.poc.domain.item.dto.ChangeItemDto
import com.poc.domain.item.dto.DeleteItemDto
import com.poc.domain.item.dto.ItemDto
import com.poc.domain.item.dto.ListItemDto
import com.poc.domain.item.repository.ItemRepository
import com.poc.domain.item_category.repository.ItemCategoryRepository
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.MultiValueMap
import org.springframework.web.multipart.MultipartFile

@Service
class ItemService {

    @Autowired
    private lateinit var s3Service: S3Service
    @Autowired
    private lateinit var itemRepository: ItemRepository
    @Autowired
    private lateinit var itemCategoryRepository: ItemCategoryRepository

    @Transactional
    fun upload(file: MultipartFile, itemCategoryId: Long): ItemDto {
        var originName = file.originalFilename
        if(originName == null) originName = ""
        val fileUrl = s3Service.upload(file)

        val itemCategory = itemCategoryRepository.findById(itemCategoryId)
            .orElseThrow{throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }

        val savedItem = itemRepository.save(Item(itemCategory, originName, fileUrl))
        return ItemDto.fromEntity(savedItem)
    }

    @Transactional(readOnly = true)
    fun getItem(itemId: Long): ItemDto {
        val item = itemRepository.findById(itemId)
            .orElseThrow{throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }
        return ItemDto.fromEntity(item)
    }

    @Transactional(readOnly = true)
    fun getItemList(itemCategoryId: Long?, pageRequest: PageRequest): ListItemDto {
        val page = itemRepository.getItemList(itemCategoryId, pageRequest)
        return ListItemDto(page.content, page.totalElements)
    }

    @Transactional
    fun deleteItemList(deleteItemDto: DeleteItemDto): Boolean {
        if(deleteItemDto.itemIdList.isEmpty()) {
            throw BadRequestException(BaseResponseCode.BAD_REQUEST, "삭제 item은 1개 이상 필요합니다.")
        }
        itemRepository.deleteByIdIn(deleteItemDto.itemIdList)
        return true
    }

    @Transactional
    fun changeItemList(changeItemDto: ChangeItemDto): Boolean {
        if(changeItemDto.itemIdList.isEmpty()) {
            throw BadRequestException(BaseResponseCode.BAD_REQUEST, "삭제 item은 1개 이상 필요합니다.")
        }

        val itemCategory = itemCategoryRepository.findById(changeItemDto.itemCategoryId)
            .orElseThrow{throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }

        for (itemId in changeItemDto.itemIdList) {
            val item = itemRepository.findById(itemId)
                .orElseThrow{throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }
            item.changeItemCategory(itemCategory)
        }

        return true
    }


}