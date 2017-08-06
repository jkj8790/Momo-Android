package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Pin(
    val id: Long,
    val authorName: String,
    val name: String,
    val momoMapId: Long,
    val type: PinType,
    val createdDate: Date,
    val postIds: List<Long>
) {
  enum class PinType {
    PLACE, FOOD, CAFE, SHOP, ETC
  }
}
