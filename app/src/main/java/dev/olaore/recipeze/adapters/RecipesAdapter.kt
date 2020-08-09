package dev.olaore.recipeze.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.databinding.ItemRecipeBinding
import dev.olaore.recipeze.models.domain.Recipe

class RecipesAdapter(val context: Context) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(ItemRecipeBinding.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = currentList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class RecipeViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {  }
        }

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
        }

    }

}

class RecipesDiffUtil : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.title == oldItem.title
    }

}