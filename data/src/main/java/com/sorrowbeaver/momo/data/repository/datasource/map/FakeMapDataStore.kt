package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.sorrowbeaver.momo.data.repository.datasource.user.FakeUserDataStore
import io.reactivex.Observable
import java.util.Date

class FakeMapDataStore(userDataStore: FakeUserDataStore) : MapDataStore {

  val FAKE_MAP = MomoMapEntity(
      0, "FAKE1", "I'm fake map",
      false, userDataStore.ME, listOf(), Date()
  )

  override fun createMap(): Observable<MomoMapEntity> {
    return Observable.just(FAKE_MAP)
  }

  override fun maps(): Observable<List<MomoMapEntity>> {
    return Observable.just(listOf(FAKE_MAP))
  }

  override fun detail(id: Long): Observable<MomoMapEntity> {
    return Observable.just(FAKE_MAP)
  }

}