package com.sorrowbeaver.momo.data.repository.datasource.pin

import com.sorrowbeaver.momo.data.entity.PinEntity
import io.reactivex.Observable
import java.util.Date
import javax.inject.Inject

class FakePinDataStore @Inject constructor() : PinDataStore {

  private val fakePin = PinEntity(
    0,
    "pin_name",
    0, Date(),
    0, "author",
    0, listOf()
  )

  override fun createPin(
    name: String,
    pinLabel: Int,
    authorId: Long,
    authorName: String,
    momoMapId: Long
  ): Observable<PinEntity> {
    return Observable.just(fakePin)
  }

  override fun pins(): Observable<List<PinEntity>> {
    return Observable.just(listOf(fakePin))
  }

  override fun detail(id: Long): Observable<PinEntity> {
    return Observable.just(fakePin)
  }
}
