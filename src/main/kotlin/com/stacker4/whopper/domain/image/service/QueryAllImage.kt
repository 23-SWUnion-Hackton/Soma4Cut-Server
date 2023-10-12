package com.stacker4.whopper.domain.image.service

import com.stacker4.whopper.common.aws.AwsS3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryAllImage(
    private val awsS3Util: AwsS3Util
) {
    fun execute() {
//        awsS3Util.
    }
}