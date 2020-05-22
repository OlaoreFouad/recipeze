package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_results_table")
data class SearchResult(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    var query: String,

    var summary: String,

    var dateAdded: Long

)