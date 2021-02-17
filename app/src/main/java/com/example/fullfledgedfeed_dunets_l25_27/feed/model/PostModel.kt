package com.example.fullfledgedfeed_dunets_l25_27.feed.model

import com.example.fullfledgedfeed_dunets_l25_27.shared.model.UserStatus

data class PostModel(
    val userId: Int,
    val title: String,
    val body: String,
    val userStatus: UserStatus
)