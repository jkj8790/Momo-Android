package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.usecase.CreateMap.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable
import javax.inject.Inject

open class CreateMap @Inject constructor(
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
