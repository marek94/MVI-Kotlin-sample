package com.example.mvi_java_kotlin.network;

import com.example.mvi_java_kotlin.kotlin.data.Comment;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface CommentApi {

    String API_URL = "http://jsonplaceholder.typicode.com/";

    @GET("/comments")
    Observable<List<Comment>> getComments();
}
