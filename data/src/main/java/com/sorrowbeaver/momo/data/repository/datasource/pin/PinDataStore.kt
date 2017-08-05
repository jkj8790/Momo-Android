package com.sorrowbeaver.momo.data.repository.datasource.pin

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.sorrowbeaver.momo.data.entity.PinEntity
import io.reactivex.Observable

interface PinDataStore {

  fun createPin(name: String, pinLabel: Int, map: MomoMapEntity): Observable<PinEntity>

  fun pins(): Observable<List<PinEntity>>

  fun detail(id: Long): Observable<PinEntity>
}