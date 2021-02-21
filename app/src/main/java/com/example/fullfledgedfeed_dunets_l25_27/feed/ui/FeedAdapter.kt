package com.example.fullfledgedfeed_dunets_l25_27.feed.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fullfledgedfeed_dunets_l25_27.R
import com.example.fullfledgedfeed_dunets_l25_27.databinding.PostBannedItemBinding
import com.example.fullfledgedfeed_dunets_l25_27.databinding.PostNormalItemBinding
import com.example.fullfledgedfeed_dunets_l25_27.feed.model.PostModelUi
import com.example.fullfledgedfeed_dunets_l25_27.shared.extensions.isVisible

class FeedAdapter :
    androidx.recyclerview.widget.ListAdapter<PostModelUi, RecyclerView.ViewHolder>(PostsDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is PostModelUi.BannedPostModelUi) R.layout.post_banned_item
        else R.layout.post_normal_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.post_normal_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                PostNormalViewHolder(view)
            }
            R.layout.post_banned_item -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false)
                BannedUserViewHolder(view)
            }
            else -> PostNormalViewHolder(View(parent.context))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostNormalViewHolder -> holder.bind(getItem(position) as PostModelUi.NormalPostModelUi)
            is BannedUserViewHolder -> holder.bind(getItem(position) as PostModelUi.BannedPostModelUi)
        }
    }

}

class PostNormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PostNormalItemBinding.bind(itemView)

    fun bind(post: PostModelUi.NormalPostModelUi) {
        binding.apply {
            tvTitle.text = post.title
            tvDescription.text = post.body
            tvWarning.isVisible(post.hasWarning)
            root.setBackgroundColor(post.bgColorInt)
            tvUser.text = itemView.context.getString(R.string.author, post.userId.toString())
        }
    }
}

class BannedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PostBannedItemBinding.bind(itemView)

    fun bind(post: PostModelUi.BannedPostModelUi) {
        binding.tvTitle.text = post.title
    }
}

class PostsDiffCallback : DiffUtil.ItemCallback<PostModelUi>() {
    override fun areItemsTheSame(oldItem: PostModelUi, newItem: PostModelUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostModelUi, newItem: PostModelUi): Boolean {
        return oldItem == newItem
    }

}
