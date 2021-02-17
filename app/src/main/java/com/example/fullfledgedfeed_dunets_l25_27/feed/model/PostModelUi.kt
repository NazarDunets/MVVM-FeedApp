package com.example.fullfledgedfeed_dunets_l25_27.feed.model

import androidx.annotation.ColorInt

data class PostModelUi(
    val userId: Int,
    val title: String,
    val body: String,
    val hasWarning: Boolean,
    val isBanned: Boolean,
    @ColorInt val bgColorInt: Int
)