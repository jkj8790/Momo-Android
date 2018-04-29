package com.sorrowbeaver.momo.data.di

import com.sorrowbeaver.momo.data.entity.mapper.UserEntityDataMapper
import com.sorrowbeaver.momo.data.repository.datasource.map.MapDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.pin.PinDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.post.PostDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.search.SearchDataRepository
import com.sorrowbeaver.momo.data.repository.datasource.user.UserDataRepository
import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.repository.PinRepository
import com.sorrowbeaver.momo.domain.repository.PostRepository
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import com.sorrowbeaver.momo.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module(includes = [DatabaseModule::class, RepositoryModule.Binder::class])
class RepositoryModule {

  @Provides
  @Reusable
  fun provideUserEntityDataMapper() = UserEntityDataMapper()

  @Module
  interface Binder {
    @Binds
    fun mapRepository(impl: MapDataRepository): MapRepository

    @Binds
    fun pinRepository(impl: PinDataRepository): PinRepository

    @Binds
    fun postRepository(impl: PostDataRepository): PostRepository

    @Binds
    fun serachRepository(impl: SearchDataRepository): SearchRepository

    @Binds
    fun userRepository(impl: UserDataRepository): UserRepository
  }
}
