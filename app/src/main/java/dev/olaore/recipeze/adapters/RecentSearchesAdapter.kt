package dev.olaore.recipeze.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.R
import dev.olaore.recipeze.listeners.OnPreferenceInteraction
import dev.olaore.recipeze.listeners.OnRecentSearchInteraction
import dev.olaore.recipeze.models.domain.Preference
import dev.olaore.recipeze.models.domain.RecentSearch

class RecentSearchesAdapter(
    private val onRecentSearchInteraction: OnRecentSearchInteraction
) : ListAdapter<RecentSearch, RecentSearchesAdapter.RecentSearchViewHolder>(RecentSearchDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : RecentSearchViewHolder = RecentSearchViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_recent_searches, parent, false
        ), onRecentSearchInteraction
    )

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val search = getItem(position)
        holder.bind(search)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class RecentSearchViewHolder
    constructor(
        private val view: View,
        private val onRecentSearchInteraction: OnRecentSearchInteraction
    ) : RecyclerView.ViewHolder(view) {

        private val searchContent: TextView = view.findViewById(R.id.item_recent_search)
        private val cancelImage: ImageView = view.findViewById(R.id.remove_item)

        fun bind(search: RecentSearch) {

            searchContent.text = search.content
            cancelImage.setOnClickListener {
                onRecentSearchInteraction.onDeleteSearch(search.id)
            }
            view.setOnClickListener {
                onRecentSearchInteraction.onSelectSearch(search.content)
            }

        }

    }

}

class RecentSearchDiffUtil : DiffUtil.ItemCallback<RecentSearch>() {

    override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
        return oldItem.id == newItem.id && oldItem.content == newItem.content
    }
}