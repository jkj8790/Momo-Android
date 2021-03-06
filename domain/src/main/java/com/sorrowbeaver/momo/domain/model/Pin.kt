package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Pin (
    val id : Long,
    val author : User,
    val name : String,
    val map : Map,
    val label : Int,
    val createdDate : Date,
    val posts : List<Post>
)
