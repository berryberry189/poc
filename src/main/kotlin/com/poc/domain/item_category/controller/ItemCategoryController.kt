package com.poc.domain.item_category.controller

import com.poc.domain.item_category.dto.ItemCategoryProjectionDto
import com.poc.domain.item_category.service.ItemCategoryService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/poc/v1/item-category")
@Api(value = "ItemCategoryController", tags = ["아이템 카테고리 관리 관련 API 입니다"])
class ItemCategoryController {

    @Autowired
    private lateinit var itemCategoryService: ItemCategoryService


    @ApiOperation(value = "아이템 카테고리 목록")
    @GetMapping("")
    fun itemCategoryList(): ResponseEntity<List<ItemCategoryProjectionDto>> {
        return ResponseEntity<List<ItemCategoryProjectionDto>>(itemCategoryService.getItemCategorList(), HttpStatus.OK)
    }

}