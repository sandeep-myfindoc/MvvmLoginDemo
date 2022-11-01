package com.example.mvvmlogindemo.repo

import androidx.lifecycle.MutableLiveData
import com.example.mvvmlogindemo.data.quoteData.QuoteList
import com.example.mvvmlogindemo.network.ApiService

class QuoteRepositry(private val quoteService:ApiService) {
    private var quoteList =  MutableLiveData<QuoteList>()

    suspend fun getQoutes(page: Int): QuoteList?{
        val result = quoteService.getQuotes(page)
        return result.body()
    }
}