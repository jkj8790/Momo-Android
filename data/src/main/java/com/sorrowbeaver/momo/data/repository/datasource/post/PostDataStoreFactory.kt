package com.sorrowbeaver.momo.data.repository.datasource.post

class PostDataStoreFactory {

  fun create() : PostDataStore {
    return FakePostDataStore()
  }

}