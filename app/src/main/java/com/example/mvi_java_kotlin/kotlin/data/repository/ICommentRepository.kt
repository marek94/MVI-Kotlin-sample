package com.example.mvi_java_kotlin.kotlin.data.repository

import com.example.mvi_java_kotlin.kotlin.data.Comment
import io.reactivex.Observable

interface ICommentRepository {
    fun getAllComments(): Observable<List<Comment>>
}