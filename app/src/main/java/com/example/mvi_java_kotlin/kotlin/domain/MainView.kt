package com.example.mvi_java_kotlin.kotlin.domain

import com.example.mvi_java_kotlin.kotlin.data.Comment
import io.reactivex.Observable

interface MainView {
    fun render(commentState: CommentState)

    fun searchMovieIntent(): Observable<Boolean>
}