package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetMapDetail.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

class GetMapDetail(
  private val mapRepository: MapRepository
) : UseCase<MomoMap, Params> {

  data class Params(val id: Long)

  override fun execute(params: Params): Observable<MomoMap> {
    return mapRepository.detail(params.id)
  }
}
