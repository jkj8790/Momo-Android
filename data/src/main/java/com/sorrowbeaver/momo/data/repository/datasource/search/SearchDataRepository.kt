package com.sorrowbeaver.momo.data.repository.datasource.search

import com.sorrowbeaver.momo.data.entity.mapper.PlaceEntityDataMapper
import com.sorrowbeaver.momo.data.entity.mapper.SearchResultEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.model.SearchResult
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import io.reactivex.Observable

class SearchDataRepository : SearchRepository {

  val searchDataStoreFactory = SearchDataStoreFactory()
  val searchDataStore = searchDataStoreFactory.create()
  val searchDataEntityDataMapper = SearchResultEntityDataMapper()
  val placeEntityDataMapper = PlaceEntityDataMapper()

  override fun searchMapAndUser(keyword: String): Observable<SearchResult> {
    return searchDataStore.searchMapAndUser(keyword)
        .map { searchDataEntityDataMapper.transform(it) }
  }

  override fun searchPlace(): Observable<List<Place>> {
    return searchDataStore.searchPlace()
        .map { placeEntityDataMapper.transform(it) }
  }

}
