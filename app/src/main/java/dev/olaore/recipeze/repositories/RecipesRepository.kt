package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import dev.olaore.recipeze.database.RecipesDatabase
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.network.Network
import dev.olaore.recipeze.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesRepository(val database: RecipesDatabase) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    lateinit var recipes: LiveData<NetworkRecipeRandomContainer>

    init {
        ioScope.launch {
            recipes = getRandomRecipes("ALL", 10)
        }
    }

    suspend fun getRandomRecipes(tags: String, number: Int): LiveData<NetworkRecipeRandomContainer> {
        val withTag = tags != "ALL"

        return liveData {
            val tag = "HomeFragment"
            Log.d(tag, "Here? - in the repo")
            if (!withTag) {
                Network.recipesService.getRandomRecipes(number, Utils.API_KEY)
            } else {
                Network.recipesService.getRandomRecipesWithTag(tags, number, Utils.API_KEY)
            }
        }

    }


}