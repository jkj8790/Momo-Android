package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.GetMaps.Params
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetMaps (
    val mapRepository: MapRepository,
    executorScheduler: Scheduler,
    postExecutionScheduler: Scheduler
) : UseCase<List<MomoMap>, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val sortOption: MapSortOption)

  override fun buildObservable(params: Params): Observable<List<MomoMap>> {
    return mapRepository.maps(params.sortOption)
  }
}
