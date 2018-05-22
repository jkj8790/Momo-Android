package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.model.MomoMapModel

open class MomoMapModelDataMapper {

  open fun transform(momoMap: MomoMap): MomoMapModel {
    return MomoMapModel(
      momoMap.id, momoMap.name, momoMap.description,
      momoMap.isPrivate, momoMap.authorId, momoMap.pinIds,
      momoMap.createdDate
    )
  }

  fun transform(maps: List<MomoMap>): List<MomoMapModel> {
    return maps.map { transform(it) }
  }
}
