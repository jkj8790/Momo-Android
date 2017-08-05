package com.sorrowbeaver.momo.data.entity

import java.util.Date

data class PostEntity(
    val pk: Long,
    val pin: Long,
    val photo: String,
    val description: String,
    val created_date: Date
)
