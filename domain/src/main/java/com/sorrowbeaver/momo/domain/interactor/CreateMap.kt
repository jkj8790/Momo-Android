package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class CreateMap (
    val mapRepository: MapRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<MomoMap, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildUseCaseObservable(params: Unit): Observable<MomoMap> {
    return mapRepository.createMap()
  }
}
