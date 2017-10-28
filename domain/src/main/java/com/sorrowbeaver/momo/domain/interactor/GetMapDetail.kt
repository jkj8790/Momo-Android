package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetMapDetail.Params
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetMapDetail (
    val mapRepository: MapRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<MomoMap, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val id: Long)

  override fun buildObservable(params: Params): Observable<MomoMap> {
    return mapRepository.detail(params.id)
  }
}
