package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.olaore.recipeze.models.database.DatabaseCuisine
import dev.olaore.recipeze.models.database.DatabaseDiet
import dev.olaore.recipeze.models.database.DatabaseUser
import dev.olaore.recipeze.models.database.SearchResult

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: DatabaseUser)

    @Update
    suspend fun updateUser(user: DatabaseUser)

    @Query("SELECT * FROM users_table WHERE pin = :pin")
    suspend fun getUser(pin: String): DatabaseUser

    @Query("SELECT diets from users_table WHERE id = :id")
    suspend fun getDietsForUser(id: Int): String

    @Query("SELECT cuisines from users_table WHERE id = :id")
    suspend fun getCuisinesForUser(id: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiet(diet: DatabaseDiet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCuisine(cuisine: DatabaseCuisine)

    @Query("SELECT * FROM search_results_table ORDER BY dateAdded DESC")
    suspend fun getSearchResults(): LiveData<List<SearchResult>>

    @Insert
    suspend fun addSearchResult(result: SearchResult)

    @Delete
    suspend fun deleteSearchResult(result: SearchResult)

}