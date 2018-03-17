package com.sorrowbeaver.momo.data.di

import com.sorrowbeaver.momo.domain.repository.MapRepository
import com.sorrowbeaver.momo.domain.repository.PinRepository
import com.sorrowbeaver.momo.domain.repository.PostRepository
import com.sorrowbeaver.momo.domain.repository.SearchRepository
import com.sorrowbeaver.momo.domain.repository.UserRepository
import dagger.Component

@Component(modules = [RepositoryModule::class])
interface DataComponent {
  fun mapRepository(): MapRepository
  fun pinRepository(): PinRepository
  fun postRepository(): PostRepository
  fun searchRepository(): SearchRepository
  fun userRepository(): UserRepository
}