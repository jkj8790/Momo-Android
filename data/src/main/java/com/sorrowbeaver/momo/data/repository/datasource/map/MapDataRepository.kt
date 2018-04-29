package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.mapper.MomoMapEntityDataMapper
import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.MomoMap.MapSortOption
import com.sorrowbeaver.momo.domain.repository.MapRepository
import io.reactivex.Observable
import javax.inject.Inject

class MapDataRepository @Inject constructor(
  private val mapDataStore: FakeMapDataStore,
  private val mapEntityDataMapper: MomoMapEntityDataMapper
) : MapRepository {

  override fun createMap(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long,
    authorName: String
  ): Observable<MomoMap> {
    return mapDataStore.createMap(name, description, isPrivate, authorId, authorName)
      .map { mapEntityDataMapper.transform(it) }
  }

  override fun maps(mapSortOption: MapSortOption): Observable<List<MomoMap>> {
    return mapDataStore.maps()
      .map { mapEntityDataMapper.transform(it)!! }
    //TODO find better solution
  }

  override fun detail(id: Long): Observable<MomoMap> {
    return mapDataStore.detail(id)
      .map { mapEntityDataMapper.transform(it) }
  }
}
