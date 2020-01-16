package com.example.mvi_java_kotlin.kotlin.data

import com.example.mvi_java_kotlin.kotlin.data.repository.ICommentRepository
import com.example.mvi_java_kotlin.kotlin.domain.CommentState
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CommentInteractor @Inject constructor(private val commentRepository: ICommentRepository) : Interactor {
    override fun getComents(): Observable<CommentState> =
        commentRepository
            .getAllComments()
            .subscribeOn(Schedulers.io())
            .map<CommentState> { CommentState.DataState(it) }
            .onErrorReturn { CommentState.ErrorState(it.message ?: "") }
}