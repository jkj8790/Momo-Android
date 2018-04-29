package com.sorrowbeaver.momo.data.repository.datasource.map

import com.sorrowbeaver.momo.data.entity.MomoMapEntity
import com.sorrowbeaver.momo.data.repository.datasource.user.FakeUserDataStore
import io.reactivex.Observable
import java.util.Date
import javax.inject.Inject

class FakeMapDataStore @Inject constructor(
  userDataStore: FakeUserDataStore
) : MapDataStore {

  private val fakeMap = MomoMapEntity(
    0, "FAKE1", "I'm fake map",
    false, 0, "a", emptyList(), Date()
  )

  override fun createMap(
    name: String,
    description: String,
    isPrivate: Boolean,
    authorId: Long,
    authorName: String
  ): Observable<MomoMapEntity> {
    return Observable.just(fakeMap)
  }

  override fun maps(): Observable<List<MomoMapEntity>> {
    return Observable.just(listOf(fakeMap))
  }

  override fun detail(id: Long): Observable<MomoMapEntity> {
    return Observable.just(fakeMap)
  }
}
