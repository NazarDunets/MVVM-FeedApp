package com.example.fullfledgedfeed_dunets_l25_27.shared.repository

import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModel
import com.example.fullfledgedfeed_dunets_l25_27.shared.api.PostsService
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.AsyncOperation
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.Multithreading
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.Result
import com.example.fullfledgedfeed_dunets_l25_27.shared.database.PseudoDatabase
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostData
import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostsErrors
import com.example.fullfledgedfeed_dunets_l25_27.shared.repository.mappers.PostsMapper
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val multithreading: Multithreading,
    private val postsService: PostsService,
    private val postsMapper: PostsMapper,
    private val database: PseudoDatabase
) {

    fun getPosts(): AsyncOperation<Result<List<PostModel>, PostsErrors>> {
        val asyncOperation = multithreading.async<Result<List<PostData>, PostsErrors>> {
            val posts = postsService.getPosts().execute().body()
                ?: return@async Result.error(PostsErrors.POSTS_NOT_LOADED)

            database.updatePostsFromRemote(posts)
            return@async Result.success(database.getCachedPosts())
        }
        return asyncOperation.map(postsMapper::map)
    }

    fun submitLocalPost(post: PostData) {
        database.submitLocalPost(post)
    }
}