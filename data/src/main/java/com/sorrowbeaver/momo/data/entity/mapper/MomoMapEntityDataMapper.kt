package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.sorrowbeaver.momo.domain.model.MomoMap
import java.util.Date

class MomoMapEntityDataMapper {

  val userEntityDataMapper = UserEntityDataMapper()

  fun transform(momoMapEntity: MomoMapEntity) : MomoMap {
    val user = userEntityDataMapper.transform(momoMapEntity.author)

    return MomoMap(
        momoMapEntity.pk, momoMapEntity.map_name, momoMapEntity.description,
        momoMapEntity.is_private, user, listOf(), Date()
    )
  }

  fun transform(momoMapEntities: List<MomoMapEntity>) : List<MomoMap> {
    return momoMapEntities.map { transform(it) }
  }

}
