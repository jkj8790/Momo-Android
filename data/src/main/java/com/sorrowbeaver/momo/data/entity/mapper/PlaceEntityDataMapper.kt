package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.PlaceEntity
import com.sorrowbeaver.momo.domain.model.Place

class PlaceEntityDataMapper {

  fun transform(placeEntity: PlaceEntity) : Place {
    return Place(
        placeEntity.id, placeEntity.latitude,
        placeEntity.longitude, placeEntity.name,
        placeEntity.address
    )
  }

  fun transform(placeEntities: List<PlaceEntity>) : List<Place> {
    return placeEntities.map { transform(it) }
  }

}
