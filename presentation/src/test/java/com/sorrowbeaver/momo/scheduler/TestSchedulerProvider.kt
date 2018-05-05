package com.sorrowbeaver.momo.scheduler

import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider(
  private val scheduler: TestScheduler
) : SchedulerProvider {
  override fun ui() = scheduler
  override fun computation() = scheduler
  override fun io() = scheduler
}