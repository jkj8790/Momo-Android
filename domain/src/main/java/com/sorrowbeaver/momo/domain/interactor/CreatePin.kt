package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.CreatePin.Params
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class CreatePin(
  val pinRepository: PinRepository,
  executorScheduler: Scheduler,
  postExecutionScheduler: Scheduler
) : UseCase<Pin, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(
    val name: String,
    val pinType: PinType,
    val authorId: Long,
    val authorName: String,
    val mapId: Long
  )

  override fun buildObservable(params: Params): Observable<Pin> {
    return pinRepository.createPin(
      params.name, params.pinType,
      params.authorId, params.authorName, params.mapId
    )
  }
}
