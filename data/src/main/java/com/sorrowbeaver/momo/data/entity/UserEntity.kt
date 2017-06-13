package com.sorrowbeaver.momo.data.entity

import java.util.Date

// TODO change any to proper class
data class UserEntity(
    val pk : Long,
    val username : String,
    val password : String,
    val email : String,
    val profile_img : String?,
    val relation_user_set : List<Long>,
    val date_joined: Date,
    val last_login: Date?,
    val is_facebook : Boolean,
    val is_active : Boolean,
    val is_staff : Boolean,
    val is_superuser : Boolean,
    val map_list: List<Any>
)
