package com.example.fullfledgedfeed_dunets_l25_27.di

import android.content.Context
import com.example.fullfledgedfeed_dunets_l25_27.shared.database.PseudoDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun getContext(): Context = context

    @Provides
    @Singleton
    fun provideDatabase(): PseudoDatabase = PseudoDatabase

}