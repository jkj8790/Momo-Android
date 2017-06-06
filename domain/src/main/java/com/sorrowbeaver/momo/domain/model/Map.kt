package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Map (
    val id : Long,
    val name : String,
    val description : String,
    val isPrivate : Boolean,
    val author : User,
    val pins : List<Pin>,
    val createdDate : Date
)
