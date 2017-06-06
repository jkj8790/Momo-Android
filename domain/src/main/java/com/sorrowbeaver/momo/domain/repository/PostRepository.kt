package com.sorrowbeaver.momo.domain.repository

import com.sorrowbeaver.momo.domain.model.Pin
import com.sorrowbeaver.momo.domain.model.Post
import io.reactivex.Observable

interface PostRepository {

  fun posts(): Observable<List<Post>>

  fun createPost(pin: Pin, photoUrl: String?, desciption: String?): Observable<Post>

  fun detail(id: Long): Observable<Post>

}