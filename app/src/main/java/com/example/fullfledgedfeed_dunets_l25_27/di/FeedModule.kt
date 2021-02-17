package com.example.fullfledgedfeed_dunets_l25_27.di

import com.example.fullfledgedfeed_dunets_l25_27.shared.api.PostsService
import com.example.fullfledgedfeed_dunets_l25_27.shared.repository.UsersRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class FeedModule {
    @Provides
    @Singleton
    fun getUsersRepository(): UsersRepository = UsersRepository()

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }

    @Provides
    @Singleton
    fun providePostsApi(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }

}