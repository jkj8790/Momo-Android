package com.sorrowbeaver.momo.domain.usecase

import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import com.sorrowbeaver.momo.domain.usecase.type.UseCase
import io.reactivex.Observable

class SearchPlace(
  private val searchRepository: SearchRepository
) : UseCase<List<Place>, Unit> {

  override fun execute(params: Unit): Observable<List<Place>> {
    return searchRepository.searchPlace()
  }
}
