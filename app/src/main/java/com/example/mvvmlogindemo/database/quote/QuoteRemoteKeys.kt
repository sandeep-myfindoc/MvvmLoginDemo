package com.example.mvvmlogindemo.database.quote

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity (tableName = "QuoteRemoteKeys")
data class QuoteRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevKey: Int?,
    val nextKey: Int?
) {
}