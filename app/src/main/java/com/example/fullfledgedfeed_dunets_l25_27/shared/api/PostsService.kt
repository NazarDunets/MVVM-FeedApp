package com.example.fullfledgedfeed_dunets_l25_27.shared.api

import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostData
import retrofit2.Call
import retrofit2.http.GET

interface PostsService {
    @GET("/posts")
    fun getPosts(): Call<List<PostData>>
}