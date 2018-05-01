package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreateMap.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

open
class CreateMap(
  private val mapRepository: MapRepository
) : UseCase<MomoMap, Params> {

  data class Params(
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val authorId: Long,
    val authorName: String
  )

  override fun execute(params: Params): Observable<MomoMap> {
    return mapRepository.createMap(
      params.name, params.description, params.isPrivate,
      params.authorId, params.authorName
    )
  }
}
