package com.example.fullfledgedfeed_dunets_l25_27

import android.app.Application
import com.example.fullfledgedfeed_dunets_l25_27.di.AppComponent
import com.example.fullfledgedfeed_dunets_l25_27.di.AppModule
import com.example.fullfledgedfeed_dunets_l25_27.di.DaggerAppComponent

class AppApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}