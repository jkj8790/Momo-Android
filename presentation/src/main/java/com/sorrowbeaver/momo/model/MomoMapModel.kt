package com.sorrowbeaver.momo.model

import java.util.Date

data class MomoMapModel(
  val id: Long,
  val name: String,
  val description: String,
  val isPrivate: Boolean,
  val authorId: Long,
  val pinIds: List<Long>,
  val createdDate: Date
)