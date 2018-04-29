package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.Place
import com.sorrowbeaver.momo.domain.model.SearchResult
import io.reactivex.Observable

interface SearchRepository {

  fun searchMapAndUser(keyword: String): Observable<SearchResult>

  fun searchPlace(): Observable<List<Place>>
}
