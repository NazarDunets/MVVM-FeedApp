package com.example.fullfledgedfeed_dunets_l25_27.shared.repository.mappers

import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModel
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.Result
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostData
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostsErrors
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.UserStatus
import com.example.fullfledgedfeed_dunets_l25_27.shared.repository.UsersRepository
import javax.inject.Inject

class PostsMapper @Inject constructor(
    private val usersRepository: UsersRepository
) {
    fun map(postsResult: Result<List<PostData>, PostsErrors>): Result<List<PostModel>, PostsErrors> {
        val bannedUsers = usersRepository.getUsersWithBan()
        val userWithWarning = usersRepository.getUsersWithWarning()

        return postsResult.mapSuccess { posts ->
            posts.map {
                when (it.userId) {
                    in bannedUsers -> PostModel(it.id, it.userId, "", "", UserStatus.BANNED)
                    in userWithWarning -> PostModel(
                        it.id,
                        it.userId,
                        it.title,
                        it.body,
                        UserStatus.WARNING
                    )
                    else -> PostModel(it.id, it.userId, it.title, it.body, UserStatus.NORMAL)
                }
            }

        }
    }
}