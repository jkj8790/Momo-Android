package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.usecase.GetMapDetail.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class GetMapDetail(
  private val mapRepository: MapRepository
) : UseCase<MomoMap, Params> {

  data class Params(val id: Long)

  override fun execute(params: Params): Observable<MomoMap> {
    return mapRepository.detail(params.id)
  }
}
