package com.example.mvvmlogindemo.database.quote

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvmlogindemo.modal.quoteResponse.Result

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addQuotes(quotes: List<Result>)

    @Query("delete from Quote")
    suspend fun deleteQuotes()

    @Query("select * from Quote")
    fun getQuotes(): PagingSource<Int,Result>
}