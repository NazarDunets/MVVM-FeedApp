package com.example.fullfledgedfeed_dunets_l25_27.feed.model

import androidx.annotation.ColorInt

sealed class PostModelUi(val id: Int, val userId: Int) {
    class NormalPostModelUi(
        id: Int,
        userId: Int,
        val title: String,
        val body: String,
        val hasWarning: Boolean,
        @ColorInt val bgColorInt: Int
    ) : PostModelUi(id, userId)

    class BannedPostModelUi(
        id: Int,
        userId: Int,
        val title: String
    ) : PostModelUi(id, userId)

}