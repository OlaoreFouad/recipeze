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
    fun addUser(user: DatabaseUser)

    @Update
    fun updateUser(user: DatabaseUser)

    @Query("SELECT * FROM users_table LIMIT 1")
    fun getUser(): LiveData<DatabaseUser>

    @Query("SELECT diets from users_table WHERE id = :id")
    fun getDietsForUser(id: Int): String

    @Query("SELECT cuisines from users_table WHERE id = :id")
    fun getCuisinesForUser(id: Int): String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addDiet(vararg diet: DatabaseDiet)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCuisine(vararg cuisine: DatabaseCuisine)

    @Query("SELECT * FROM diets_table")
    fun getStoredDiets(): LiveData<List<DatabaseDiet>>

    @Query("SELECT * FROM cuisines_table")
    fun getStoredCuisines(): LiveData<List<DatabaseCuisine>>

    @Query("SELECT * FROM search_results_table ORDER BY dateAdded DESC")
    fun getSearchResults(): LiveData<List<SearchResult>>

    @Insert
    fun addSearchResult(result: SearchResult)

    @Delete
    fun deleteSearchResult(result: SearchResult)

}