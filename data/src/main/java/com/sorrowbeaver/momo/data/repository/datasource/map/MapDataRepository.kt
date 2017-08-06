package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.mapper.MomoMapEntityDataMapper
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable

class MapDataRepository : MapRepository {

  val mapDataStore = MapDataStoreFactory().create()
  val mapDataEntityDataMapper = MomoMapEntityDataMapper()

  override fun createMap(): Observable<MomoMap> {
    return mapDataStore.createMap()
        .map { mapDataEntityDataMapper.transform(it) }
  }

  override fun maps(mapSortOption: MapSortOption): Observable<List<MomoMap>> {
    return mapDataStore.maps()
        .map { mapDataEntityDataMapper.transform(it) }
  }

  override fun detail(id: Long): Observable<MomoMap> {
    return mapDataStore.detail(id)
        .map { mapDataEntityDataMapper.transform(it) }
  }

}
