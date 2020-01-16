package com.example.mvi_java_kotlin.kotlin.data.repository

import com.example.mvi_java_kotlin.network.CommentApi
import javax.inject.Inject

class CommentRepository @Inject constructor(val api: CommentApi) : ICommentRepository {
    override fun getAllComments() = api.comments
}