package com.sorrowbeaver.momo

interface BaseView<in T> {
  fun setPresenter(presenter: T)
}
