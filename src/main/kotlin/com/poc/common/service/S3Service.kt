package com.poc.common.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.util.IOUtils
import com.poc.exception.BadRequestException
import com.poc.exception.BaseResponseCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.IOException
import java.util.*

@Service
class S3Service (
    private val s3Client: AmazonS3Client
) {

    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucket: String

    val dir: String = "poc/"

    @Throws(IOException::class)
    fun upload(file: MultipartFile): String {
        val fileName = UUID.randomUUID().toString() + "-" + file.originalFilename
        val objMeta = ObjectMetadata()

        val bytes = IOUtils.toByteArray(file.inputStream)
        objMeta.contentType = file.contentType
        objMeta.contentLength = bytes.size.toLong()

        val byteArrayIs = ByteArrayInputStream(bytes)

        try {
            s3Client.putObject(
                PutObjectRequest(bucket, dir + fileName, byteArrayIs, objMeta)
                    .withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: Exception) {
            throw BadRequestException(BaseResponseCode.ERR_FILE_EXCEPTION, "파일 저장에 실패했습니다.")
        }

        return s3Client.getUrl(bucket, dir + fileName).toString()
    }


}

