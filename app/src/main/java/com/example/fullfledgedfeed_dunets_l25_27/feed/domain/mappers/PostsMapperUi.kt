package com.example.fullfledgedfeed_dunets_l25_27.feed.domain.mappers

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.fullfledgedfeed_dunets_l25_27.R
import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModel
import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModelUi
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.Result
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostsErrors
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.UserStatus
import javax.inject.Inject

class PostsMapperUi @Inject constructor(private val context: Context) {
    fun map(postsResult: Result<List<PostModel>, PostsErrors>): Result<List<PostModelUi>, String> {
        return postsResult.mapSuccess { posts ->
            posts.map {
                val hasWarning = it.userStatus == UserStatus.WARNING
                val isBanned = it.userStatus == UserStatus.BANNED

                val bgColor = if (hasWarning) {
                    ContextCompat.getColor(context, R.color.warning_post_bg)
                } else {
                    ContextCompat.getColor(context, R.color.normal_post_bg)
                }

                val title =
                    if (isBanned) context.getString(R.string.user_banned, it.userId.toString())
                    else it.title

                PostModelUi(
                    userId = it.userId,
                    title = title,
                    body = it.body,
                    hasWarning = hasWarning,
                    isBanned = isBanned,
                    bgColorInt = bgColor
                )
            }
        }.mapError { postsErrors ->
            val errorStringRes = when (postsErrors) {
                PostsErrors.POSTS_NOT_LOADED -> R.string.error_posts_not_loaded
                PostsErrors.MOCK_ERROR -> R.string.error_mock
            }

            context.getString(errorStringRes)
        }
    }
}