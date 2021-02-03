package dev.olaore.recipeze.network

import android.util.Log
import dev.olaore.recipeze.models.network.NetworkRecipeInformation
import dev.olaore.recipeze.models.network.NetworkRecipeInstructionContainer
import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.models.network.NetworkRecipeSummary
import dev.olaore.recipeze.utils.Utils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//
//private val loggingInterceptor = Interceptor { chain ->
//    var request = chain.request()
//    Log.d("OkHttp", "Request going out...")
//    val builder: Request.Builder = request.newBuilder().addHeader("Cache-Control", "no-cache")
//    request = builder.build()
//
//    chain.proceed(request)
//}

private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(Utils.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

interface RecipesService {

    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") number: Int,
        @Query("apiKey") key: String
    ): NetworkRecipeRandomContainer

    @GET("random")
    suspend fun getRandomRecipesWithTag(
        @Query("tags") tags: String,
        @Query("number") number: Int,
        @Query("apiKey") key: String
    ): NetworkRecipeRandomContainer

    @GET("{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") recipeId: Int,
        @Query("includeNutrition") includeNutrition: Boolean = false,
        @Query("apiKey") key: String
    ): NetworkRecipeInformation

    @GET("{id}/summary")
    suspend fun getRecipeSummary(
        @Path("id") recipeId: Int,
        @Query("apiKey") key: String
    ): NetworkRecipeSummary

    @GET("{id}/analyzedInstructions")
    suspend fun getAnalyzedInstructions(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String
    ): List<NetworkRecipeInstructionContainer>

}

object Network {

    val recipesService: RecipesService by lazy {
        retrofit.create(RecipesService::class.java)
    }

}

class RecipesApiHelper(private val apiService: RecipesService) {

    suspend fun getAllRecipes() = apiService.getRandomRecipes(10, Utils.API_KEY)

    suspend fun getRecipesWithTag(tag: String) = apiService.getRandomRecipesWithTag(tag.toLowerCase(), 10, Utils.API_KEY)

    suspend fun getRecipeDetails(id: Int) = apiService.getRecipeDetails(id, key = Utils.API_KEY)

    suspend fun getRecipeSummary(id: Int) = apiService.getRecipeSummary(id, Utils.API_KEY)

    suspend fun getRecipeInstructions(id: Int) = apiService.getAnalyzedInstructions(id, Utils.API_KEY)

}