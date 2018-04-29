package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.SearchMapAndUser.Params
import com.sorrowbeaver.momo.domain.model.SearchResult
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.Scheduler

class SearchMapAndUser(
  val searchRepository: SearchRepository,
  executorScheduler: Scheduler,
  postExecutionScheduler: Scheduler
) : UseCase<SearchResult, Params>(executorScheduler, postExecutionScheduler) {

  data class Params(val keyword: String)

  override fun buildObservable(params: Params): Observable<SearchResult> {
    return searchRepository.searchMapAndUser(params.keyword)
  }
}
