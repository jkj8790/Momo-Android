package com.sorrowbeaver.momo

interface BasePresenter<in T> {
  fun subscribe()
  fun unsubscribe()
  fun takeView(view: T)
}
