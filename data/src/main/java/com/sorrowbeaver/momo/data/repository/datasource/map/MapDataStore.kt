package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import io.reactivex.Observable

interface MapDataStore {

  fun createMap(): Observable<MomoMapEntity>

  fun maps(): Observable<List<MomoMapEntity>>

  fun detail(id: Long): Observable<MomoMapEntity>

}
