package com.poc.domain.archive.repository

import com.poc.domain.archive.Archive
import org.springframework.data.jpa.repository.JpaRepository

interface ArchiveRepository: JpaRepository<Archive, Long?>, ArchiveRepositoryCustom {
}