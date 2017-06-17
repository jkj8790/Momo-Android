package com.sorrowbeaver.momo.domain.interactor


import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).

 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
abstract class UseCase<T, in Params> constructor(
    private val executorScheduler: Scheduler,
    private val postExecutionScheduler: Scheduler) {
  private val disposables: CompositeDisposable = CompositeDisposable()

  /**
   * Builds an [Observable] which will be used when executing the current [UseCase].
   */
  abstract fun buildUseCaseObservable(params: Params): Observable<T>

  /**
   * Executes the current use case.

   * @param observer [DisposableObserver] which will be listening to the observable build
   * * by [.buildUseCaseObservable] ()} method.
   * *
   * @param params Parameters (Optional) used to build/execute this use case.
   */
  fun execute(observer: DisposableObserver<T>, params: Params) {
    val observable = this.buildUseCaseObservable(params)
        .subscribeOn(executorScheduler)
        .observeOn(postExecutionScheduler)
    addDisposable(observable.subscribeWith(observer))
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  fun dispose() {
    if (!disposables.isDisposed) {
      disposables.dispose()
    }
  }

  /**
   * Dispose from current [CompositeDisposable].
   */
  private fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }
}
