package com.sorrowbeaver.momo.domain.interactor

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).

 */
abstract class UseCase<T, in Params> constructor(
  private val executorScheduler: Scheduler,
  private val postExecutionScheduler: Scheduler
) {
  private val disposables: CompositeDisposable = CompositeDisposable()
  private val schedulersTransformer = ObservableTransformer<Any, Any> { upstream ->
    upstream!!
      .subscribeOn(Schedulers.io())
      .observeOn(postExecutionScheduler)
  }

  /**
   * Builds an [Observable] which will be used when executing the current [UseCase].
   */
  abstract fun buildObservable(params: Params): Observable<T>

  fun get(params: Params): Observable<T> {
    return buildObservable(params).compose(applySchedulers())
  }

  @Suppress("Unchecked_cast")
  private fun <O> applySchedulers(): ObservableTransformer<O, O> {
    return schedulersTransformer as ObservableTransformer<O, O>
  }
}
