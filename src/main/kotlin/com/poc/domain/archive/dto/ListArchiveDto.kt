package com.poc.domain.archive.dto

data class ListArchiveDto (
    val contents: List<ArchiveProjectionDto>,
    val totalCount : Long
)