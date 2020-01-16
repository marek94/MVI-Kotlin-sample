package com.example.mvi_java_kotlin.kotlin.di

import com.example.mvi_java_kotlin.kotlin.data.CommentInteractor
import com.example.mvi_java_kotlin.kotlin.data.Interactor
import com.example.mvi_java_kotlin.kotlin.data.repository.CommentRepository
import com.example.mvi_java_kotlin.kotlin.data.repository.ICommentRepository
import com.example.mvi_java_kotlin.network.CommentApi
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [InteractorModule::class, ApiModuleBindings::class])
class ApiModule {
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, @ApiURL apiUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideGson() = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient()

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit) = retrofit.create(CommentApi::class.java)
}

@Module
abstract class InteractorModule {
    @Singleton
    @Binds
    abstract fun bindCommentInteractor(commentInteractor: CommentInteractor): Interactor
}

@Module
abstract class ApiModuleBindings {
    @Singleton
    @Binds
    abstract fun bindCommentRepository(commentRepository: CommentRepository): ICommentRepository
}