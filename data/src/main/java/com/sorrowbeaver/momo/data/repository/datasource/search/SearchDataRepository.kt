package com.sorrowbeaver.momo.data.repository.datasource.search

import com.sorrowbeaver.momo.data.entity.mapper.PlaceEntityDataMapper
import com.sorrowbeaver.momo.data.entity.mapper.SearchResultEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.model.SearchResult
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable
import javax.inject.Inject

class SearchDataRepository @Inject constructor(
  private val searchDataStore: FakeSearchDataStore,
  private val searchEntityDataMapper: SearchResultEntityDataMapper,
  private val placeEntityDataMapper: PlaceEntityDataMapper
) : SearchRepository {

  override fun searchMapAndUser(keyword: String): Observable<SearchResult> {
    return searchDataStore.searchMapAndUser(keyword)
      .map { searchEntityDataMapper.transform(it) }
  }

  override fun searchPlace(): Observable<List<Place>> {
    return searchDataStore.searchPlace()
      .map { placeEntityDataMapper.transform(it) }
  }
}
