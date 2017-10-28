package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class User(
    val id: Long,
    val userName: String,
    val email: String,
    val profileUrl: String?,
    val joinedDate: Date,
    val lastLoggedInDate: Date?,
    // TODO encapsulate with social feature
    val signedUpWithFacebook: Boolean,
    val activated: Boolean,
    val type: UserType,
    val momoMapIds: List<Long>,
    val following: List<User>
)

