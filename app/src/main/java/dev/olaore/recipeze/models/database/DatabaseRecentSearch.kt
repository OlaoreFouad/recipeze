package dev.olaore.recipeze.models.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "recent_searches_table" )
data class DatabaseRecentSearch(

    @PrimaryKey
    val id: String,

    val content: String,

    val createdOn: Long
)