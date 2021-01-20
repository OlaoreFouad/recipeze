package dev.olaore.recipeze.listeners

import dev.olaore.recipeze.models.domain.RecipeIngredient

interface OnRecipeIngredientsProvided {

    fun provideIngredients(ingredients: List<RecipeIngredient>)

}