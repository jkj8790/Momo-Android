package com.sorrowbeaver.momo.data.entity

// TODO change any to proper class
data class UserEntity(
    val pk : Long,
    val username : String,
    val password : String,
    val email : String,
    val profile_img : Any?,
    val relation_user_set : Any?,
    val date_joined: Any?,
    val last_login: Any?,
    val is_facebook : Boolean,
    val is_active : Boolean,
    val is_staff : Boolean,
    val is_superuser : Boolean,
    val map_list: Any?
)
