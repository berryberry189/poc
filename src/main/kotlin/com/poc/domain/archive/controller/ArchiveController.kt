package com.poc.domain.archive.controller

import com.poc.domain.archive.dto.ArchiveDto
import com.poc.domain.archive.dto.ListArchiveDto
import com.poc.domain.archive.service.ArchiveService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/poc/v1/archive")
@Api(value = "ArchiveController", tags = ["아카이브 관리 관련 API 입니다"])
class ArchiveController {

    @Autowired
    private lateinit var archiveService: ArchiveService

    @ApiOperation(value = "아카이브 저장")
    @PostMapping("")
    fun saveArchive(@RequestPart(value = "file", required = true) file: MultipartFile,
                    @RequestParam(value = "name", required = true) name: String,
                    @RequestParam(value = "team", required = true) team: String,
                    @RequestParam(value = "position", required = true) position: String, ): ResponseEntity<ArchiveDto> {
        return ResponseEntity<ArchiveDto>(archiveService.saveArchive(file, name, team, position), HttpStatus.OK)
    }

    @ApiOperation(value = "아카이브 목록")
    @GetMapping("")
    fun archiveList(
        @RequestParam(value = "searchKeyword", required = false) searchKeyword: String?,
        @RequestParam(value = "page", required = true) page : Int,
        @RequestParam(value = "size", required = true) size : Int
    ) : ResponseEntity<ListArchiveDto> {
        return ResponseEntity<ListArchiveDto>(archiveService.getArchiveList(searchKeyword, PageRequest.of(page-1, size)), HttpStatus.OK)
    }

    @ApiOperation(value = "아카이브 상세")
    @GetMapping("/{archiveId}")
    fun archive(@PathVariable("archiveId") archiveId : Long) : ResponseEntity<ArchiveDto> {
        return ResponseEntity<ArchiveDto>(archiveService.getArchive(archiveId), HttpStatus.OK)
    }
}