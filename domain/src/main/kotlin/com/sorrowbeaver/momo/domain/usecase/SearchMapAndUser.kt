package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.SearchResult
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import com.sorrowbeaver.momo.domain.usecase.SearchMapAndUser.Params
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class SearchMapAndUser(
  private val searchRepository: SearchRepository
) : UseCase<SearchResult, Params> {

  data class Params(val keyword: String)

  override fun execute(params: Params): Observable<SearchResult> {
    return searchRepository.searchMapAndUser(params.keyword)
  }
}
