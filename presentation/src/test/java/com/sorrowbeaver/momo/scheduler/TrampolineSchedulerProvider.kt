package com.sorrowbeaver.momo.scheduler

import io.reactivex.schedulers.Schedulers

object TrampolineSchedulerProvider : SchedulerProvider {
  override fun io() = Schedulers.trampoline()
  override fun ui() = Schedulers.trampoline()
  override fun computation() = Schedulers.trampoline()
}
