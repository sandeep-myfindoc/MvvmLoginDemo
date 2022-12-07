package com.example.mvvmlogindemo.database.quote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteRemoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllRemoteKey(quoteRemoteKeys: List<QuoteRemoteKeys>)
    @Query("delete from QuoteRemoteKeys")
    fun deleteAllRemoteKey()
    @Query("select * from QuoteRemoteKeys where id = :id")
    fun getRemoteKey(id: String): QuoteRemoteKeys
}