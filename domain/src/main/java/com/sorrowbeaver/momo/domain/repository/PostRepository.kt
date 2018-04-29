package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.Post
import io.reactivex.Observable

interface PostRepository {

  fun posts(): Observable<List<Post>>

  fun createPost(pinId: Long, photoUrl: String?, description: String?): Observable<Post>

  fun detail(id: Long): Observable<Post>
}
