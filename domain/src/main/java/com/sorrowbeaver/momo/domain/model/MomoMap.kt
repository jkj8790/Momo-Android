package com.sorrowbeaver.momo.domain.model

import java.util.Date

data class MomoMap(
  val id: Long,
  val name: String,
  val description: String,
  val isPrivate: Boolean,
  val authorId: Long,
  val pinIds: List<Long>,
  val createdDate: Date
) {
  enum class MapSortOption {
    RECENT, PIN_COUNT
  }
}
