package com.example.fullfledgedfeed_dunets_l25_27.feed.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fullfledgedfeed_dunets_l25_27.feed.domain.mappers.PostsMapperUi
import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModelUi
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.CancelableOperation
import com.example.fullfledgedfeed_dunets_l25_27.shared.async.Result
import com.example.fullfledgedfeed_dunets_l25_27.shared.repository.PostsRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val postsRepository: PostsRepository,
    private val postsMapperUi: PostsMapperUi
) : ViewModel() {

    val viewState = MutableLiveData<FeedViewState>()

    private var cancelableOperation: CancelableOperation? = null

    fun fetchPosts() {
        viewState.value = FeedViewState.Loading

        cancelableOperation = postsRepository.getPosts()
            .map(postsMapperUi::map)
            .postOnMainThread(::updateFeed)
    }

    private fun updateFeed(result: Result<List<PostModelUi>, String>) {
        if (result.isError) {
            viewState.value = FeedViewState.PostsLoadError(result.errorResult)
        } else {
            viewState.value = FeedViewState.PostsLoadSuccess(result.successResult)
        }
    }

}

sealed class FeedViewState {
    object Loading : FeedViewState()
    data class PostsLoadSuccess(val posts: List<PostModelUi>) : FeedViewState()
    data class PostsLoadError(val error: String) : FeedViewState()
}