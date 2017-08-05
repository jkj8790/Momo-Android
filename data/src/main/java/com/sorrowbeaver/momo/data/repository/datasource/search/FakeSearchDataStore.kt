package com.sorrowbeaver.momo.data.repository.datasource.search

import com.sorrowbeaver.momo.data.entity.PlaceEntity
import com.sorrowbeaver.momo.data.entity.SearchResultEntity
import io.reactivex.Observable

class FakeSearchDataStore : SearchDataStore {

  override fun searchMapAndUser(keyword: String): Observable<SearchResultEntity> {
    TODO("not implemented")
  }

  override fun searchPlace(): Observable<List<PlaceEntity>> {
    TODO("not implemented")
  }

}