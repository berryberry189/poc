package com.poc.domain.archive.dto

import java.time.LocalDateTime

data class ArchiveProjectionDto (
    val archiveId: Long? = null,
    val originName: String,
    val fileUrl: String,
    val name: String,
    val team: String,
    val position: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)