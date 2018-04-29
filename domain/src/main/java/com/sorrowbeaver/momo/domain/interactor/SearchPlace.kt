package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class SearchPlace(
  private val searchRepository: SearchRepository,
  executorScheduler: Scheduler,
  postExecutionScheduler: Scheduler
) : UseCase<List<Place>, Unit>(executorScheduler, postExecutionScheduler) {

  override fun buildObservable(params: Unit): Observable<List<Place>> {
    return searchRepository.searchPlace()
  }
}
