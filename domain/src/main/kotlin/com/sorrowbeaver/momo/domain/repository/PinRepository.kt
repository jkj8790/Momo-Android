package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import io.reactivex.Observable

interface PinRepository {

  fun createPin(
    name: String,
    pinType: PinType,
    authorId: Long,
    authorName: String,
    mapId: Long
  ): Observable<Pin>

  fun getAllPins(): Observable<List<Pin>>

  fun getPinsByMapId(mapId: Long): Observable<List<Pin>>

  fun detail(id: Long): Observable<Pin>
}
