package com.sorrowbeaver.momo.data.repository.datasource

class UserDataStoreFactory {

  fun create() : UserDataStore {
    return FakeUserDataStore()
  }

}

