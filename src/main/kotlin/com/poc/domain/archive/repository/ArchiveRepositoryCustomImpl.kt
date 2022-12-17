package com.poc.domain.archive.repository

import com.poc.domain.archive.QArchive.archive
import com.poc.domain.archive.dto.ArchiveProjectionDto
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class ArchiveRepositoryCustomImpl (
    val queryFactory: JPAQueryFactory
) : ArchiveRepositoryCustom {

    override
    fun getArchiveList(searchKeyword: String?, pageRequest: PageRequest): Page<ArchiveProjectionDto> {
        val content = queryFactory
            .select(
                Projections.constructor(
                    ArchiveProjectionDto::class.java,
                    archive.id.`as`("archiveId"),
                    archive.originName,
                    archive.fileUrl,
                    archive.name,
                    archive.team,
                    archive.position,
                    archive.createdAt,
                    archive.updatedAt
            ))
            .from(archive)
            .where(searchKeyword(searchKeyword))
            .orderBy(archive.id.asc())
            .offset(pageRequest.offset)
            .limit(pageRequest.pageSize.toLong())
            .fetch()

        val countQuery: JPAQuery<Long> = queryFactory
            .select(archive.count())
            .from(archive)
            .where(searchKeyword(searchKeyword))

        return PageableExecutionUtils.getPage(
            content, pageRequest
        ) { countQuery.fetchOne()!! }
    }

    fun searchKeyword(searchKeyword: String?) : BooleanBuilder? {
        if(searchKeyword == null) return null
        val builder = BooleanBuilder()
        builder.or(archive.name.contains(searchKeyword));
        builder.or(archive.team.contains(searchKeyword));
        builder.or(archive.position.contains(searchKeyword));
        return builder
    }


}