package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPins (
    val pinRepository: PinRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<Pin>, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildObservable(params: Unit): Observable<List<Pin>> {
    return pinRepository.pins()
  }
}
