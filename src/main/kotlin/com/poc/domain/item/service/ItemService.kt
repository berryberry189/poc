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
import org.springframework.web.multipart.MultipartFile
import java.util.*

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


    @Transactional(readOnly = true)
    fun getRandomItemList(): List<ItemDto> {
        var randomItemList = ArrayList<ItemDto>()

        var random = Random()
        var randomNum = 0

        // 얼굴 1개
        val faceList = itemRepository.findAllByItemCategory_Id(1)
        if(faceList.isNotEmpty()) {
            randomNum = random.nextInt(faceList.size)
            randomItemList.add(ItemDto.fromEntity(faceList[randomNum]))
        }

        // 앞머리 1개
        val frontHairList = itemRepository.findAllByItemCategory_Id(5)
        if(frontHairList.isNotEmpty()) {
            randomNum = random.nextInt(frontHairList.size)
            randomItemList.add(ItemDto.fromEntity(frontHairList[randomNum]))
        }

        // 뒷머리 1개
        val backHairList  = itemRepository.findAllByItemCategory_Id(6)
        if(backHairList.isNotEmpty()) {
            randomNum = random.nextInt(backHairList.size)
            randomItemList.add(ItemDto.fromEntity(backHairList[randomNum]))
        }

        // 상의 1개
        val topList  = itemRepository.findAllByItemCategory_Id(3)
        if(topList.isNotEmpty()) {
            randomNum = random.nextInt(topList.size)
            randomItemList.add(ItemDto.fromEntity(topList[randomNum]))
        }

        // 아이템 1개
        val itemList = itemRepository.findAllByItemCategory_IdIn(listOf(7, 8, 9))
        if(itemList.isNotEmpty()) {
            randomNum = random.nextInt(itemList.size)
            randomItemList.add(ItemDto.fromEntity(itemList[randomNum]))
        }

        return randomItemList
    }


}