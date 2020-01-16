package com.example.mvi_java_kotlin.kotlin.data

data class Comment(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)