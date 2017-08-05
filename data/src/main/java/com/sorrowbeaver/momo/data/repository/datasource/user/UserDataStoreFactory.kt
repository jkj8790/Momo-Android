package com.sorrowbeaver.momo.data.repository.datasource.user

class UserDataStoreFactory {

  fun create() : UserDataStore {
    return FakeUserDataStore()
  }

}

