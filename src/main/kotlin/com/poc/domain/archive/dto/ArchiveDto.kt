package com.poc.domain.archive.dto

import com.poc.domain.archive.Archive
import java.time.LocalDateTime

data class ArchiveDto(
    val archiveId: Long? = null,
    val originName: String,
    val fileUrl: String,
    val name: String,
    val team: String,
    val position: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
) {

    companion object {
        fun fromEntity(archive: Archive): ArchiveDto {
            return archive.run {
                ArchiveDto(
                    archiveId = id,
                    originName = originName,
                    fileUrl = fileUrl,
                    name = name,
                    team = team,
                    position = position,
                    createdAt = createdAt,
                    updatedAt = updatedAt
                )
            }
        }
    }


}