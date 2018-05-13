package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.PinEntity
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType.CAFE
import com.sorrowbeaver.momo.domain.model.Pin.PinType.ETC
import com.sorrowbeaver.momo.domain.model.Pin.PinType.FOOD
import com.sorrowbeaver.momo.domain.model.Pin.PinType.PLACE
import com.sorrowbeaver.momo.domain.model.Pin.PinType.SHOP
import javax.inject.Inject

class PinEntityDataMapper @Inject constructor() {

  fun transform(pinEntity: PinEntity): Pin {
    val type = when (pinEntity.pinLabel) {
      0 -> PLACE
      1 -> FOOD
      2 -> CAFE
      3 -> SHOP
      4 -> ETC
      else -> ETC
    }

    return Pin(
      pinEntity.id, pinEntity.name, type, pinEntity.createdAt,
      pinEntity.authorId, pinEntity.authorName, pinEntity.mapId,
      pinEntity.postIds
    )
  }

  fun transform(pinEntities: List<PinEntity>): List<Pin> {
    return pinEntities.map { transform(it) }
  }
}
