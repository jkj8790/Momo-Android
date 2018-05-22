package com.sorrowbeaver.momo.mapper

import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.model.LocationModel

class LocationModelDataMapper {
  fun transform(location: Location) = LocationModel(
    location.latitude,
    location.longitude
  )
}
