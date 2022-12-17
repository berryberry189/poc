package com.poc.domain.archive.service

import com.poc.common.service.S3Service
import com.poc.domain.archive.Archive
import com.poc.domain.archive.dto.ArchiveDto
import com.poc.domain.archive.dto.ListArchiveDto
import com.poc.domain.archive.repository.ArchiveRepository
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class ArchiveService {

    @Autowired
    private lateinit var s3Service: S3Service

    @Autowired
    private lateinit var archiveRepository: ArchiveRepository

    @Transactional
    fun saveArchive(file: MultipartFile, name: String, team: String, position: String): ArchiveDto {
        var originName = file.originalFilename
        if(originName == null) originName = ""
        val fileUrl = s3Service.upload(file)
        val savedArchive = archiveRepository.save(Archive(originName, fileUrl, name, team, position))
        return ArchiveDto.fromEntity(savedArchive)
    }

    @Transactional(readOnly = true)
    fun getArchiveList(searchKeyword: String?, pageRequest: PageRequest): ListArchiveDto {
        val page = archiveRepository.getArchiveList(searchKeyword, pageRequest)
        return ListArchiveDto(page.content, page.totalElements)
    }

    @Transactional(readOnly = true)
    fun getArchive(archiveId: Long): ArchiveDto {
        val archive = archiveRepository.findById(archiveId)
            .orElseThrow{throw BadRequestException(BaseResponseCode.ERR_KEY_ARG_IS_WRONG) }
        return ArchiveDto.fromEntity(archive)
    }


}