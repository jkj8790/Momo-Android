package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class User(
    val id: Long,
    val type: UserType,
    val userName: String,
    val email: String,
    val profileUrl: String?,
    val joinedDate: Date,
    val lastLoggedInDate: Date?,
    // TODO encapsulate with social feature
    val signedUpWithFacebook: Boolean,
    val activated: Boolean,
    val momoMapIds: List<Long>,
    val followeeIds: List<Long>,
    val followerIds: List<Long>
)

