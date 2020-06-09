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
import dev.olaore.recipeze.models.domain.Preference

class PreferencesAdapter(
    private val context: Context
) : ListAdapter<Preference, PreferencesAdapter.PreferenceViewHolder>(PreferenceDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : PreferenceViewHolder
            = PreferenceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_preference, parent, false), context)

    override fun onBindViewHolder(holder: PreferenceViewHolder, position: Int) {
        val preference = getItem(position)
        holder.bind(preference)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class PreferenceViewHolder
        constructor(
        private val view: View,
        private val context: Context
    ) : RecyclerView.ViewHolder(view) {

        private val preferenceView = view.findViewById<View>(R.id.preference_view)
        private val preferenceName = view.findViewById<TextView>(R.id.preference_name)
        private val preferenceCheck = view.findViewById<ImageView>(R.id.preference_check)
        private val preferenceMore = view.findViewById<ImageButton>(R.id.preference_more_info)

        init {
            view.setOnClickListener {  }
        }

        fun bind(preference: Preference) {
            preferenceView.setBackgroundColor(
                context.resources.getColor(if (preference.isSelected) R.color.colorPrimaryDark else R.color.colorPrimaryLight)
            )

            preferenceName.text = preference.name
            preferenceCheck.setImageResource(
                if (preference.isSelected) R.drawable.ic_check_circle_selected else R.drawable.ic_check_circle_unselected
            )

            view.setBackgroundResource(
                if (preference.isSelected) R.drawable.background_preference_selected else R.drawable.background_preference_unselected
            )
        }

    }

}

class PreferenceDiffUtil : DiffUtil.ItemCallback<Preference>() {
    override fun areItemsTheSame(oldItem: Preference, newItem: Preference): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Preference, newItem: Preference): Boolean {
        return oldItem.name == newItem.name && oldItem.isSelected == newItem.isSelected
    }
}