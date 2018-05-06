package com.sorrowbeaver.momo.rule

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TrampolineSchedulerRule : TestRule {
  override fun apply(base: Statement?, description: Description?): Statement {
    return object : Statement() {
      override fun evaluate() {
        val trampoline = Schedulers.trampoline()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampoline }
        RxJavaPlugins.setInitComputationSchedulerHandler { trampoline }
        RxJavaPlugins.setInitIoSchedulerHandler { trampoline }

        try {
          base?.evaluate()
        } finally {
          RxAndroidPlugins.reset()
          RxJavaPlugins.reset()
        }
      }
    }
  }
}
