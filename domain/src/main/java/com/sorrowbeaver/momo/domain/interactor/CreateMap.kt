package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreateMap.Params
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

open
class CreateMap(
  private val mapRepository: MapRepository,
  executorScheduler: Scheduler,
  postExecutionScheduler: Scheduler
) : UseCase<MomoMap, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(
    val name: String,
    val description: String,
    val isPrivate: Boolean,
    val authorId: Long,
    val authorName: String
  )

  override fun buildObservable(params: Params): Observable<MomoMap> {
    return mapRepository.createMap(
      params.name, params.description, params.isPrivate,
      params.authorId, params.authorName
    )
  }
}
