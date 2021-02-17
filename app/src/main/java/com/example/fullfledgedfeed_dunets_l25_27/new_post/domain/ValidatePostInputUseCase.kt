package com.example.fullfledgedfeed_dunets_l25_27.new_post.domain

import android.content.Context
import com.example.fullfledgedfeed_dunets_l25_27.R
import javax.inject.Inject

class ValidatePostInputUseCase @Inject constructor(private val context: Context) {

    private val forbiddenWords = setOf("реклама", "куплю", "товар")
    private val forbiddenRegex =
        forbiddenWords.joinToString(prefix = "(?i)", separator = "|").toRegex()

    fun validate(title: String, body: String): NewPostViewState {
        val titleErrors = mutableListOf<String>()
        val bodyErrors = mutableListOf<String>()

        if (title.length < 3) {
            titleErrors.add(context.getString(R.string.too_short_error))
        } else if (title.length > 50) {
            titleErrors.add(context.getString(R.string.too_long_error))
        }

        if (body.length < 5) {
            bodyErrors.add(context.getString(R.string.too_short_error))
        } else if (body.length > 500) {
            bodyErrors.add(context.getString(R.string.too_long_error))
        }

        if (title.contains(forbiddenRegex)) {
            titleErrors.add(context.getString(R.string.forbidden_words_error))
        }

        return if (titleErrors.isNotEmpty() || bodyErrors.isNotEmpty()) {
            NewPostViewState.InvalidPost(titleErrors, bodyErrors)
        } else {
            NewPostViewState.ValidPost
        }

    }

}