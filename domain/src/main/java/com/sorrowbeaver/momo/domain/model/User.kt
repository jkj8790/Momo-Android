package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val profileUrl: String?,
    val joinedDate: Date,
    val lastLoggedInDate: Date?,
    // TODO encapsulate with social feature
    val signedUpWithFacebook: Boolean,
    val activated: Boolean,
    val type: UserType,
    val momoMapList: List<MomoMap>,
    val following: List<User>
)

