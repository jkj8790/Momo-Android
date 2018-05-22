package com.sorrowbeaver.momo.dummy

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

object DummyMapRepository : MapRepository {
  override fun createMap(name: String, description: String, isPrivate: Boolean, authorId: Long): Observable<MomoMap> {
    throw NotImplementedError()
  }

  override fun getAllMaps(mapSortOption: MomoMap.MapSortOption): Observable<List<MomoMap>> {
    throw NotImplementedError()
  }

  override fun getMapsByUserId(userId: Long): Observable<List<MomoMap>> {
    throw NotImplementedError()
  }

  override fun detail(id: Long): Observable<MomoMap> {
    throw NotImplementedError()
  }
}