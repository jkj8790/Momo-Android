package com.sorrowbeaver.momo.data.repository.datasource.search

import com.sorrowbeaver.momo.data.entity.PlaceEntity
import com.sorrowbeaver.momo.data.entity.SearchResultEntity
import io.reactivex.Observable
import javax.inject.Inject

class FakeSearchDataStore @Inject constructor() : SearchDataStore {

  override fun searchMapAndUser(keyword: String): Observable<SearchResultEntity> {
    TODO("not implemented")
  }

  override fun searchPlace(): Observable<List<PlaceEntity>> {
    TODO("not implemented")
  }

}
