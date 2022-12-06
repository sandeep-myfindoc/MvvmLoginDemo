package com.example.mvvmlogindemo.paging.quote

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.mvvmlogindemo.modal.quoteResponse.Result
import com.example.mvvmlogindemo.network.ApiService

  class QuoteRepo(private val quoteService: ApiService) {

  fun getQuote(): LiveData<PagingData<Result>> {

        val pager = Pager(
            PagingConfig(20,60),
            null,
            {QuotePagingSource(quoteService)}
        )
        return pager.liveData
    }

}