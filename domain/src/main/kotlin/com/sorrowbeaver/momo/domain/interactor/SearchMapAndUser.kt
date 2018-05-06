package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.SearchMapAndUser.Params
import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.SearchResult
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable

class SearchMapAndUser(
  private val searchRepository: SearchRepository
) : UseCase<SearchResult, Params> {

  data class Params(val keyword: String)

  override fun execute(params: Params): Observable<SearchResult> {
    return searchRepository.searchMapAndUser(params.keyword)
  }
}
