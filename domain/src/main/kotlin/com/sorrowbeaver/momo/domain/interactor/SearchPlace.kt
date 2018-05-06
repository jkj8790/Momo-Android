package com.sorrowbeaver.momo.domain.interactor

import com.sorrowbeaver.momo.domain.interactor.type.UseCase
import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable

class SearchPlace(
  private val searchRepository: SearchRepository
) : UseCase<List<Place>, Unit> {

  override fun execute(params: Unit): Observable<List<Place>> {
    return searchRepository.searchPlace()
  }
}
