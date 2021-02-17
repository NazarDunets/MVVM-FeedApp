package com.example.fullfledgedfeed_dunets_l25_27.new_post.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class NewPostViewModel @Inject constructor(
    private val validatePostInputUseCase: ValidatePostInputUseCase,
    private val submitLocalPostUseCase: SubmitLocalPostUseCase
) : ViewModel() {

    val viewState = MutableLiveData<NewPostViewState>()

    fun checkInput(title: String, body: String) {
        viewState.value = validatePostInputUseCase.validate(title, body)
    }

    fun submitPost(title: String, body: String) {
        submitLocalPostUseCase.submit(title, body)
        viewState.value = NewPostViewState.PostSubmitted
    }
}

sealed class NewPostViewState {
    object ValidPost : NewPostViewState()
    data class InvalidPost(
        val titleErrors: List<String>,
        val bodyErrors: List<String>
    ) : NewPostViewState()

    object PostSubmitted : NewPostViewState()
}

