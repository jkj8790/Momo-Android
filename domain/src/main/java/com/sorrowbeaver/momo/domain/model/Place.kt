package com.sorrowbeaver.momo.domain.model

data class Place(
    val id: String,
    val latitude: Float,
    val longitude: Float,
    val name: String,
    val address: String
)