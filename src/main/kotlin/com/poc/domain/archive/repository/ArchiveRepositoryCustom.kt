package com.poc.domain.archive.repository

import com.poc.domain.archive.dto.ArchiveProjectionDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface ArchiveRepositoryCustom {

    fun getArchiveList(searchKeyword: String?, pageRequest: PageRequest): Page<ArchiveProjectionDto>
}