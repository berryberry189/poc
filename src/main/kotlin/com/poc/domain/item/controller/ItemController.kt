package com.poc.domain.item.controller

import com.poc.domain.item.dto.ItemDto
import com.poc.domain.item.dto.ListItemDto
import com.poc.domain.item.enum_type.ItemCategory
import com.poc.domain.item.service.ItemService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/poc/v1/item")
@Api(value = "ItemController", tags = ["아이템 관리 관련 API 입니다"])
class ItemController {

    @Autowired
    private lateinit var itemService: ItemService

    @ApiOperation(value = "아이템 저장")
    @PostMapping("")
    fun saveItem(@RequestPart(value = "file", required = true) file: MultipartFile,
                 @RequestParam itemCategory: ItemCategory): ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.upload(file, itemCategory), HttpStatus.OK)

    }

    @ApiOperation(value = "아이템 상세")
    @GetMapping("/{item_id}")
    fun item(@PathVariable("item_id") itemId : Long) : ResponseEntity<ItemDto> {
        return ResponseEntity<ItemDto>(itemService.getItem(itemId), HttpStatus.OK)
    }


    @ApiOperation(value = "아이템 목록")
    @GetMapping("")
    fun itemList(
        @RequestParam(value = "itemCategory", required = false) itemCategory: ItemCategory?,
        @RequestParam(value = "page", required = true, defaultValue = "1") page : Int,
        @RequestParam(value = "size", required = true, defaultValue = "10") size : Int
    ) : ResponseEntity<ListItemDto> {
        return ResponseEntity<ListItemDto>(itemService.getItemList(itemCategory, PageRequest.of(page-1, size)), HttpStatus.OK)
    }
}