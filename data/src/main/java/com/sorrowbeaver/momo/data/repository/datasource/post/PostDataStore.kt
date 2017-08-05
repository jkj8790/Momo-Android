package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.PinEntity
import com.sorrowbeaver.momo.data.entity.PostEntity
import io.reactivex.Observable

interface PostDataStore {

  fun posts(): Observable<List<PostEntity>>

  fun createPost(pin: PinEntity, photoUrl: String?, description: String?): Observable<PostEntity>

  fun detail(id: Long): Observable<PostEntity>
}

