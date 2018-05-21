package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.sorrowbeaver.momo.domain.model.MomoMap
import java.util.Date
import javax.inject.Inject

class
MomoMapEntityDataMapper @Inject constructor() {

  fun transform(momoMapEntity: MomoMapEntity): MomoMap {
    return MomoMap(
      momoMapEntity.pk, momoMapEntity.map_name, momoMapEntity.description,
      momoMapEntity.is_private, momoMapEntity.authorId, listOf(), Date()
    )
  }

  fun transform(momoMapEntities: List<MomoMapEntity>?): List<MomoMap>? {
    return momoMapEntities?.map { transform(it) }
  }
}
