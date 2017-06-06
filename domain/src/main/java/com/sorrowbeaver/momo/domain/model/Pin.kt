package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Pin(
    val id: Long,
    val author: User,
    val name: String,
    val momoMap: MomoMap,
    val type: PinType,
    val createdDate: Date,
    val posts: List<Post>
) {
  enum class PinType {
    PLACE, FOOD, CAFE, SHOP, ETC
  }
}
