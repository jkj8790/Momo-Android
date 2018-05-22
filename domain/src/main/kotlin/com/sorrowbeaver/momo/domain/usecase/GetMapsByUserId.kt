package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetMapsByUserId @Inject constructor(
  private val mapRepository: MapRepository
) : UseCase<List<MomoMap>, GetMapsByUserId.Params> {

  data class Params(val userId: Long)

  override fun execute(params: Params): Observable<List<MomoMap>> {
    return mapRepository.getMapsByUserId(params.userId)
  }
}