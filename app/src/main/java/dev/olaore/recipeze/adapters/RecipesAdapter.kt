package dev.olaore.recipeze.adapters


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.activities.RecipeActivity
import dev.olaore.recipeze.databinding.ItemRecipeBinding
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.utils.Utils

class RecipesAdapter(val context: Context) : ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(RecipesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(ItemRecipeBinding.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            parent,
            false
        ), context)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = currentList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    class RecipeViewHolder(val binding: ItemRecipeBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe

            binding.root.setOnClickListener {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra(Utils.RECIPE_ID_KEY, binding.recipe?.id)
                context.startActivity(intent)
            }

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