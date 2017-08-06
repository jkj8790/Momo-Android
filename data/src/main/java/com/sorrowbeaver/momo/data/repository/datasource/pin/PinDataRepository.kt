package com.sorrowbeaver.momo.data.repository.datasource.pin

import com.sorrowbeaver.momo.data.entity.mapper.PinEntityDataMapper
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import com.sorrowbeaver.momo.domain.repository.PinRepository
import io.reactivex.Observable

class PinDataRepository : PinRepository {

  val pinDataStore = PinDataStoreFactory().create()
  val pinDataEntityDataMapper = PinEntityDataMapper()

  override fun createPin(name: String, pinType: PinType, mapId: Long): Observable<Pin> {
    return pinDataStore.createPin(name, pinType.ordinal, mapId)
        .map { pinDataEntityDataMapper.transform(it) }
  }

  override fun pins(): Observable<List<Pin>> {
    return pinDataStore.pins()
        .map { pinDataEntityDataMapper.transform(it) }
  }

  override fun detail(id: Long): Observable<Pin> {
    return pinDataStore.detail(id)
        .map { pinDataEntityDataMapper.transform(it) }
  }
}
