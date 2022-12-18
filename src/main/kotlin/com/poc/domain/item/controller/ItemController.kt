package com.poc.domain.item.controller

import com.poc.domain.item.dto.ChangeItemDto
import com.poc.domain.item.dto.DeleteItemDto
import com.poc.domain.item.dto.ItemDto
import com.poc.domain.item.dto.ListItemDto
import com.poc.domain.item.service.ItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/poc/v1/item")
@Api(value = "ItemController", tags = ["아이템 관리 관련 API 입니다"])
class ItemController {

    @Autowired
    private lateinit var itemService: ItemService

    @ApiOperation(value = "아이템 저장")
    @PostMapping("")
    fun saveItem(@RequestPart(value = "file", required = true) file: MultipartFile,
                 @RequestParam itemCategoryId: Long): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.upload(file, itemCategoryId), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 상세")
    @GetMapping("/{itemId}")
    fun item(@PathVariable("itemId") itemId : Long) : ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.getItem(itemId), HttpStatus.OK)
    }


    @ApiOperation(value = "아이템 목록")
    @GetMapping("")
    fun itemList(
        @RequestParam(value = "itemCategoryId", required = false) itemCategoryId: Long?,
        @RequestParam(value = "page", required = true) page : Int,
        @RequestParam(value = "size", required = true) size : Int
    ) : ResponseEntity<ListItemDto> {
        return ResponseEntity<ListItemDto>(itemService.getItemList(itemCategoryId, PageRequest.of(page-1, size)), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 목록 삭제")
    @DeleteMapping("")
    fun deleteItemList(@Valid @RequestBody deleteItemDto: DeleteItemDto): ResponseEntity<Boolean> {
        return ResponseEntity<Boolean>(itemService.deleteItemList(deleteItemDto), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 카테고리 변경")
    @PutMapping("")
    fun changeItemList(@Valid @RequestBody changeItemDto: ChangeItemDto): ResponseEntity<Boolean> {
        return ResponseEntity<Boolean>(itemService.changeItemList(changeItemDto), HttpStatus.OK)
    }

    @ApiOperation(value = "랜덤 아이템 목록")
    @GetMapping("/random")
    fun randomItemList(): ResponseEntity<List<ItemDto>> {
        return ResponseEntity<List<ItemDto>>(itemService.getRandomItemList(), HttpStatus.OK)
    }
}