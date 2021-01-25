package dev.olaore.recipeze.fragments.recipe

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.olaore.recipeze.R
import dev.olaore.recipeze.activities.RecipeActivity
import dev.olaore.recipeze.listeners.OnRecipeIngredientsProvided
import dev.olaore.recipeze.models.domain.RecipeIngredient

class RecipeIngredientsFragment() : Fragment() {

    private var ingredients = mutableListOf<RecipeIngredient>()
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var ingredientList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ingredientList = view.findViewById(R.id.recipe_ingredients_list)

    }

    fun provideIngredients(ingredients: List<RecipeIngredient>) {
        this.ingredients.clear()
        this.ingredients.addAll(ingredients)

        ingredientsAdapter = IngredientsAdapter(requireContext(), this.ingredients)

        ingredientList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = ingredientsAdapter
        }
    }

}

class IngredientsAdapter(
    private var ctx: Context, private var ingredients: MutableList<RecipeIngredient>
) : RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        return IngredientViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_recipe_ingredient, parent, false
        ) as TextView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val ingredientContent = ingredients[position].original
        holder.bind(ingredientContent)
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    class IngredientViewHolder(var view: TextView) : RecyclerView.ViewHolder(view) {

        fun bind(ingredient: String) {
            view.text = ingredient
        }

    }

}