package com.example.mvvmlogindemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmlogindemo.database.quote.QuoteDao
import com.example.mvvmlogindemo.database.quote.QuoteRemoteDao
import com.example.mvvmlogindemo.database.quote.QuoteRemoteKeys
import com.example.mvvmlogindemo.modal.quoteResponse.Result
@Database(entities = [Result::class,QuoteRemoteKeys::class], version = 1)
abstract class QuoteDataBase:RoomDatabase() {
    abstract fun getQuoteDao():QuoteDao
    abstract fun getQuoteRemoteDao():QuoteRemoteDao

    companion object{
        @Volatile
        private var Instance: QuoteDataBase?=null
        fun getQuoteDatabase(context: Context):QuoteDataBase{
            synchronized(this){
                if(Instance == null){
                    Instance = Room.databaseBuilder(context.applicationContext,QuoteDataBase::class.java,"QuoteDataBase")
                        .build()
                }
                return Instance!!
            }

        }
    }
}