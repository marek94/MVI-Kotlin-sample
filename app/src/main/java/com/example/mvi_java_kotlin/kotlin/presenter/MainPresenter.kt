package com.example.mvi_java_kotlin.kotlin.presenter

import androidx.lifecycle.ViewModel
import com.example.mvi_java_kotlin.kotlin.data.CommentInteractor
import com.example.mvi_java_kotlin.kotlin.data.Interactor
import com.example.mvi_java_kotlin.kotlin.domain.CommentState
import com.example.mvi_java_kotlin.kotlin.domain.MainView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class MainPresenter (private val commentInteractor: Interactor): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val stateSubject = BehaviorSubject.create<CommentState>()

    fun bind(mainView: MainView) {
        val buttonClickObservable = mainView.searchMovieIntent()
            .flatMap { commentInteractor.getComents()
                .startWith(CommentState.LoadingState) }
        compositeDisposable.add(
            buttonClickObservable.subscribeWith(stateSubject).observeOn(AndroidSchedulers.mainThread()).subscribe { mainView.render(it) }
        )
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}