package com.sorrowbeaver.momo.data.entity.mapper

import com.sorrowbeaver.momo.data.entity.PostEntity
import com.sorrowbeaver.momo.domain.model.Post
import javax.inject.Inject

class PostEntityDataMapper @Inject constructor() {

  fun transform(postEntity: PostEntity) : Post {
    return Post(
        postEntity.pk, postEntity.pin, postEntity.photo,
        postEntity.description, postEntity.created_date
    )
  }

  fun transform(postEntities: List<PostEntity>) : List<Post> {
    return postEntities.map { transform(it) }
  }

}
