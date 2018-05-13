package com.sorrowbeaver.momo.data.repository.datasource.post

import com.sorrowbeaver.momo.data.entity.PostEntity
import io.reactivex.Observable

interface PostDataStore {

  fun posts(): Observable<List<PostEntity>>

  fun getPostsByPinId(pinId: Long): Observable<List<PostEntity>>

  fun getPostsByMapId(mapId: Long): Observable<List<PostEntity>>

  fun createPost(pinId: Long, photoUrl: String?, description: String?): Observable<PostEntity>

  fun detail(id: Long): Observable<PostEntity>
}
