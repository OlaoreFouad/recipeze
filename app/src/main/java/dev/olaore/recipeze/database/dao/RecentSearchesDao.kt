package dev.olaore.recipeze.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.olaore.recipeze.models.database.DatabaseRecentSearch

@Dao
interface RecentSearchesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentSearch(recentSearch: DatabaseRecentSearch)

    @Query("SELECT * FROM recent_searches_table ORDER BY createdOn LIMIT 5")
    suspend fun getRecentSearches(): LiveData<List<DatabaseRecentSearch>>

    @Query("DELETE FROM recent_searches_table WHERE id = :id")
    suspend fun deleteRecentSearch(id: String)

}