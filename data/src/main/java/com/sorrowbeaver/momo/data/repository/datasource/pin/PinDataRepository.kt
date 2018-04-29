package com.sorrowbeaver.momo.data.repository.datasource.pin

import com.sorrowbeaver.momo.data.entity.mapper.PinEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable
import javax.inject.Inject

class PinDataRepository @Inject constructor(
  private val pinDataStore: DiskPinDataStore,
  private val pinEntityDataMapper: PinEntityDataMapper
) : PinRepository {

  override fun createPin(
    name: String,
    pinType: PinType,
    authorId: Long,
    authorName: String,
    mapId: Long
  ): Observable<Pin> {
    return pinDataStore.createPin(name, pinType.ordinal, authorId, authorName, mapId)
      .map { pinEntityDataMapper.transform(it) }
  }

  override fun pins(): Observable<List<Pin>> {
    return pinDataStore.pins()
      .map { pinEntityDataMapper.transform(it) }
  }

  override fun detail(id: Long): Observable<Pin> {
    return pinDataStore.detail(id)
      .map { pinEntityDataMapper.transform(it) }
  }
}
