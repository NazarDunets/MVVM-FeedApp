package com.example.fullfledgedfeed_dunets_l25_27.new_post.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fullfledgedfeed_dunets_l25_27.AppApplication
import com.example.fullfledgedfeed_dunets_l25_27.R
import com.example.fullfledgedfeed_dunets_l25_27.databinding.NewPostFragmentBinding
import com.example.fullfledgedfeed_dunets_l25_27.new_post.domain.NewPostViewModel
import com.example.fullfledgedfeed_dunets_l25_27.new_post.domain.NewPostViewState
import javax.inject.Inject

class NewPostFragment : Fragment() {

    companion object {
        fun newInstance() = NewPostFragment()
    }

    private var _binding: NewPostFragmentBinding? = null
    private val binding get() = requireNotNull(_binding)

    @Inject
    lateinit var viewModel: NewPostViewModel

    private var titleText: String = ""
    private var bodyText: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as AppApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewPostFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setTextWatchers()
        setListeners()
    }

    private fun setTextWatchers() {
        binding.etPostTitle.addTextChangedListener(
            afterTextChanged = { text ->
                titleText = text?.toString() ?: ""
                checkInput()
            }
        )
        binding.etPostBody.addTextChangedListener(
            afterTextChanged = { text ->
                bodyText = text?.toString() ?: ""
                checkInput()
            }
        )
    }

    private fun setupObserver() {
        viewModel.viewState.observe(viewLifecycleOwner, {
            when (it) {
                is NewPostViewState.ValidPost -> {
                    binding.tlPostTitle.error = null
                    binding.tlPostBody.error = null
                    binding.btnSave.isEnabled = true
                }
                is NewPostViewState.InvalidPost -> {
                    binding.tlPostTitle.error = it.titleErrors.firstOrNull()
                    binding.tlPostBody.error = it.bodyErrors.firstOrNull()
                    binding.btnSave.isEnabled = false
                }
                is NewPostViewState.PostSubmitted -> {
                    goToFeed()
                }
            }
        })
    }

    private fun goToFeed() {
        findNavController().popBackStack(R.id.feedFragment, false)
    }

    private fun setListeners() {
        binding.btnSave.setOnClickListener {
            viewModel.submitPost(titleText, bodyText)
        }
    }

    private fun checkInput() {
        viewModel.checkInput(titleText, bodyText)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}