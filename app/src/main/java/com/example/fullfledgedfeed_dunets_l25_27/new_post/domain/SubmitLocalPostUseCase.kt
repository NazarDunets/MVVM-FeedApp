package com.example.fullfledgedfeed_dunets_l25_27.new_post.domain

import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostData
import com.example.fullfledgedfeed_dunets_l25_27.shared.repository.PostsRepository
import java.util.*
import javax.inject.Inject

class SubmitLocalPostUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {

    companion object {
        private const val USER_ID = 0
    }

    fun submit(title: String, body: String) {
        val post = PostData(
            userId = USER_ID,
            id = UUID.randomUUID().toString().hashCode(),
            title = title,
            body = body
        )
        postsRepository.submitLocalPost(post)
    }
}