package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.MomoMap
import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Pin.PinType
import io.reactivex.Observable

interface PinRepository {

  fun createPin(name: String, pinType: PinType, map: MomoMap): Observable<Pin>

  fun pins(): Observable<List<Pin>>

  fun detail(id: Long): Observable<Pin>
}
