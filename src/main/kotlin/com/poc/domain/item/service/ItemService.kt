package com.poc.domain.item.service

import com.poc.common.service.S3Service
import com.poc.domain.item.Item
import com.poc.domain.item.dto.ItemDto
import com.poc.domain.item.dto.ListItemDto
import com.poc.domain.item.dto.SaveItemDto
import com.poc.domain.item.enum_type.ItemCategory
import com.poc.domain.item.repository.ItemRepository
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ItemService {

    @Autowired
    private lateinit var s3Service: S3Service
    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Transactional
    fun upload(file: MultipartFile, itemCategory: ItemCategory): ItemDto {
        var originName = file.originalFilename
        if(originName == null) originName = ""
        val fileUrl = s3Service.upload(file)
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
    fun getItemList(itemCategory: ItemCategory?, pageRequest: PageRequest): ListItemDto {
        val page = itemRepository.getItemList(itemCategory, pageRequest)
        return ListItemDto(page.content, page.totalElements)
    }


}