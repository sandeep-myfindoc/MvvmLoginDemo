package com.example.mvvmlogindemo.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.mvvmlogindemo.modal.quoteResponse.QuoteResponse
import com.example.mvvmlogindemo.modal.quoteResponse.Result
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.NetworkResult
import com.example.mvvmlogindemo.paging.quote.QuotePagingSource
import org.json.JSONObject
import retrofit2.Response

class QuoteRepositry(private val quoteService:ApiService) {
    private var _quoteResponse =  MutableLiveData<NetworkResult<QuoteResponse>>()
    val quoteResponse: LiveData<NetworkResult<QuoteResponse>>
        get() = _quoteResponse

    /*suspend fun getQoutes(page: Int): QuoteList?{
        val result = quoteService.getQuotes(page)
        return result.body()
    }*/

    suspend fun getQoutes(page: Int){
        val result = quoteService.getQuotes(page)
        handleResponse(result)
    }
    private fun handleResponse(response: Response<QuoteResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _quoteResponse.postValue(NetworkResult.Sucess(response.body()))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _quoteResponse.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _quoteResponse.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
    fun getQuote(): LiveData<PagingData<Result>> {

        val pager = Pager(
            PagingConfig(20,60),
            null,
            { QuotePagingSource(quoteService) }
        )
        return pager.liveData
    }
}