package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetPinDetail.Params
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPinDetail (
    val pinRepository: PinRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<Pin, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val id: Long)

  override fun buildObservable(params: Params): Observable<Pin> {
    return pinRepository.detail(params.id)
  }
}
