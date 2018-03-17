package com.sorrowbeaver.momo.data.repository.datasource.pin

import com.sorrowbeaver.momo.data.entity.PinEntity
import io.reactivex.Observable
import javax.inject.Inject

class FakePinDataStore @Inject constructor() : PinDataStore {

  val FAKE_PIN = PinEntity(0, "author", "pin_name",
      0, 0, "pin_label", "2017-03-02", listOf()
  )

  override fun createPin(name: String, pinLabel: Int, momoMapId: Long): Observable<PinEntity> {
    return Observable.just(FAKE_PIN)
  }

  override fun pins(): Observable<List<PinEntity>> {
    return Observable.just(listOf(FAKE_PIN))
  }

  override fun detail(id: Long): Observable<PinEntity> {
    return Observable.just(FAKE_PIN)
  }

}
