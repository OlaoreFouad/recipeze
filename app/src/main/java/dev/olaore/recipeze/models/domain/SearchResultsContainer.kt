package dev.olaore.recipeze.models.domain

import dev.olaore.recipeze.models.database.SearchResult
import java.io.Serializable

data class SearchResultsContainer(
    val results: List<RecipeSearch>
) : Serializable