package com.example.fullfledgedfeed_dunets_l25_27.di

import com.example.fullfledgedfeed_dunets_l25_27.feed.ui.FeedFragment
import com.example.fullfledgedfeed_dunets_l25_27.new_post.ui.NewPostFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, FeedModule::class])
interface AppComponent {

    fun inject(fragment: FeedFragment)
    fun inject(fragment: NewPostFragment)
}