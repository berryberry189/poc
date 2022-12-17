package com.poc.domain.item_category_default_color.controller


import com.poc.domain.item_category_default_color.dto.ItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.dto.ListItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.dto.SaveItemCategoryDefaultColorDto
import com.poc.domain.item_category_default_color.service.ItemCategoryDefaultColorService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/poc/v1/item-category-default-color")
@Api(value = "ItemCategoryDefaultColorController", tags = ["아이템 카테고리 기본 컬러 관리 관련 API 입니다"])
class ItemCategoryDefaultColorController {

    @Autowired
    private lateinit var itemCategoryDefaultColorService: ItemCategoryDefaultColorService

    @ApiOperation(value = "아이템 카테고리 기본 컬러 목록")
    @GetMapping("/{item_category_id}")
    fun itemCategoryDefaultColorList(
        @PathVariable("item_category_id") itemCategoryId: Long): ResponseEntity<List<ItemCategoryDefaultColorDto>> {
        return ResponseEntity<List<ItemCategoryDefaultColorDto>>(
            itemCategoryDefaultColorService.getItemCategoryDefaultColorList(itemCategoryId), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 카테고리 기본 컬러 전체 목록")
    @GetMapping("")
    fun itemCategoryDefaultColorAllList(
        @RequestParam(value = "page", required = true) page : Int,
        @RequestParam(value = "size", required = true) size : Int
    ): ResponseEntity<ListItemCategoryDefaultColorDto> {
        return ResponseEntity<ListItemCategoryDefaultColorDto>(
            itemCategoryDefaultColorService.getItemCategoryDefaultColorAllList(PageRequest.of(page-1, size)), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 카테고리 기본 컬러 저장")
    @PostMapping("")
    fun saveItemCategoryDefaultColor(saveItemCategoryDefaultColorDto: SaveItemCategoryDefaultColorDto): ResponseEntity<ItemCategoryDefaultColorDto> {
        return ResponseEntity<ItemCategoryDefaultColorDto>(
            itemCategoryDefaultColorService.saveItemCategoryDefaultColor(saveItemCategoryDefaultColorDto), HttpStatus.OK)
    }

    @ApiOperation(value = "아이템 카테고리 기본 컬러 삭제")
    @DeleteMapping("/{item_category_default_color_id}")
    fun deleteItemCategoryDefaultColor(
        @PathVariable("item_category_default_color_id") itemCategoryDefaultColorId: Long
    ): ResponseEntity<Boolean> {
        return ResponseEntity<Boolean>(
            itemCategoryDefaultColorService.deleteItemCategoryDefaultColor(itemCategoryDefaultColorId), HttpStatus.OK)
    }



}