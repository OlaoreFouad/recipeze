package dev.olaore.recipeze.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import dev.olaore.recipeze.database.RecipesDatabase
import dev.olaore.recipeze.models.domain.Recipe
import dev.olaore.recipeze.models.mappers.Result
import dev.olaore.recipeze.models.mappers.asDomainModel
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.network.Network
import dev.olaore.recipeze.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesRepository(val database: RecipesDatabase) {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    suspend fun getRandomRecipes(tags: String, number: Int): LiveData<Result<List<Recipe>>> {
        val withTag = tags != "ALL"

        return liveData {
            emit(Result.LOADING(null))
            val result = Network.recipesService.getRandomRecipes(number, Utils.API_KEY).asDomainModel()
            emit(Result.SUCCESS(result))

        }
    }

    fun refreshRecipes(tag: String) {
        val refreshedRecipes = MutableLiveData<Result<List<Recipe>>>()
        Network.recipesService.getRandomRecipesWithTag(tag, 10, Utils.API_KEY).enqueue(
            object : Callback<NetworkRecipeRandomContainer> {
                override fun onResponse(
                    call: Call<NetworkRecipeRandomContainer>,
                    response: Response<NetworkRecipeRandomContainer>
                ) {
                    Log.d("RecipesRefresh", "Status Cod: " + response.code().toString())
                    if (response.isSuccessful) {
                        Log.d("RecipesRefresh", "Recipes Size: " + response.body()?.recipes?.size.toString())
                    }
                }

                override fun onFailure(call: Call<NetworkRecipeRandomContainer>, t: Throwable) {
                    Log.d("RecipesRefresh", "Error Occurred: " + t.message)
                }
            }
        )
    }


}