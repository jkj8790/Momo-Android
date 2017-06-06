package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Post(
    val id: Long,
    val pin: Pin,
    val photoUrl: String?,
    val description: String,
    val createdDate: Date
)
