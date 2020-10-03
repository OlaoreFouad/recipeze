package dev.olaore.recipeze.network

import dev.olaore.recipeze.models.network.NetworkRecipeRandomContainer
import dev.olaore.recipeze.utils.Utils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

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
    fun getRandomRecipesWithTag(
        @Query("tags") tags: String,
        @Query("number") number: Int,
        @Query("apiKey") key: String
    ): Call<NetworkRecipeRandomContainer>

}

object Network {

    val recipesService: RecipesService by lazy {
        retrofit.create(RecipesService::class.java)
    }

}