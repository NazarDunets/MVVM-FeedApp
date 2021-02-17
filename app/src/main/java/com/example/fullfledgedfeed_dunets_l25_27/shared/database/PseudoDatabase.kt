package com.example.fullfledgedfeed_dunets_l25_27.shared.database

import com.example.fullfledgedfeed_dunets_l25_27.shared.model.PostData

object PseudoDatabase {

    private val postsFromRemote: MutableList<PostData> = mutableListOf()
    private val localPosts: MutableList<PostData> = mutableListOf()

    fun updatePostsFromRemote(newList: List<PostData>) {
        postsFromRemote.clear()
        postsFromRemote.addAll(newList)
    }

    fun submitLocalPost(post: PostData) {
        localPosts.add(0, post)
    }

    fun getCachedPosts(): List<PostData> {
        return localPosts + postsFromRemote
    }
}