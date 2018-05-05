package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreateMap.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

open class CreateMap(
  private val mapRepository: MapRepository
) : UseCase<MomoMap, Params> {

  data class Params(
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val authorId: Long
  )

  override fun execute(params: Params): Observable<MomoMap> {
    if (params.name.length !in MomoMap.nameLengthRange) {
      return Observable.error(IllegalArgumentException("Name length must at be least 2"))
    }

    return mapRepository.createMap(
      params.name, params.description,
      params.isPrivate, params.authorId
    )
  }
}
