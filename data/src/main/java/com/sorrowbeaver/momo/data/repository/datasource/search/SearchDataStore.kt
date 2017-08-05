package com.sorrowbeaver.momo.data.repository.datasource.search

import com.sorrowbeaver.momo.data.entity.PlaceEntity
import com.sorrowbeaver.momo.data.entity.SearchResultEntity
import io.reactivex.Observable

interface SearchDataStore {

  fun searchMapAndUser(keyword: String): Observable<SearchResultEntity>

  fun searchPlace(): Observable<List<PlaceEntity>>
}