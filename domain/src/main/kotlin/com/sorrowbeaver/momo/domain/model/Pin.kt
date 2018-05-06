package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class Pin(
  val id: Long,
  val name: String,
  val type: PinType,
  val createAt: Date,
  val authorId: Long,
  val authorName: String,
  val momoMapId: Long,
  val postIds: List<Long>
) {
  enum class PinType {
    PLACE, FOOD, CAFE, SHOP, ETC
  }
}
