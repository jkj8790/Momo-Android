package com.sorrowbeaver.momo.data.entity

data class PinEntity(
    val pk: Long,
    val author: String,
    val pin_name: String,
    val place: Long,
    val map: Long,
    val pin_label: String,
    val created_date: String,
    val post_list: List<PostEntity>
)
