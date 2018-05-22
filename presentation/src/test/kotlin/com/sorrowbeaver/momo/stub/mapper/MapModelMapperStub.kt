package com.sorrowbeaver.momo.stub.mapper

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.mapper.MomoMapModelDataMapper
import com.sorrowbeaver.momo.model.MomoMapModel

class MapModelMapperStub(
  private val expected: MomoMapModel
) : MomoMapModelDataMapper() {

  override fun transform(momoMap: MomoMap): MomoMapModel {
    return expected
  }
}