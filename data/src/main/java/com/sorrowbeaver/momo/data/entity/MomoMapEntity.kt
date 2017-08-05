package com.sorrowbeaver.momo.data.entity

import java.util.Date

data class MomoMapEntity(
    val pk: Long,
    val map_name: String,
    val description: String,
    val is_private: Boolean,
    val author: UserEntity,
    val pins: List<PinEntity>,
    val createdDate: Date
)
