package com.example.mvi_java_kotlin.kotlin.domain

import com.example.mvi_java_kotlin.kotlin.data.Comment

sealed class CommentState {
    object LoadingState : CommentState()
    data class DataState(val data: List<Comment>) : CommentState()
    data class ErrorState(val data: String) : CommentState()
}