package com.example.mvi_java_kotlin.kotlin.data

import com.example.mvi_java_kotlin.kotlin.domain.CommentState
import io.reactivex.Observable

interface Interactor {
    fun getComents(): Observable<CommentState>
}