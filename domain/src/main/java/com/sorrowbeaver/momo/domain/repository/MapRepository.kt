package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption.RECENT
import io.reactivex.Observable

interface MapRepository {

  fun createMap(): Observable<MomoMap>

  fun maps(mapSortOption: MapSortOption = RECENT): Observable<List<MomoMap>>

  fun detail(id: Long) : Observable<MomoMap>

}
