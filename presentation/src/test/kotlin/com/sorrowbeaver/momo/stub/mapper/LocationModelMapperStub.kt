package com.sorrowbeaver.momo.stub.mapper

import com.sorrowbeaver.momo.domain.model.Location
import com.sorrowbeaver.momo.mapper.LocationModelDataMapper
import com.sorrowbeaver.momo.model.LocationModel

class LocationModelMapperStub(
  private val expected: LocationModel
) : LocationModelDataMapper() {

  override fun transform(location: Location): LocationModel {
    return expected
  }
}