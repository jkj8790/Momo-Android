package com.sorrowbeaver.momo.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {
  fun getScheduler(): Scheduler
}
