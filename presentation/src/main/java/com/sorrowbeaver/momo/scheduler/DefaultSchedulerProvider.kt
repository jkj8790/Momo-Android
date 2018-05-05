package com.sorrowbeaver.momo.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object DefaultSchedulerProvider : SchedulerProvider {
  override fun ui() = AndroidSchedulers.mainThread()
  override fun computation() = Schedulers.computation()
  override fun io() = Schedulers.io()
}