package com.example.mvvmlogindemo.paging.quote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.mvvmlogindemo.modal.quoteResponse.Result
import com.example.mvvmlogindemo.network.ApiService
// Paging source will take key and value where Key is Int that we pass with page and value is Result i.e response
class QuotePagingSource(private val quoteService: ApiService):PagingSource<Int,Result>() {
    /*
    *   @param - it will have the information that which page we want to load
    */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try{
            val position = params.key?:1
            val response = quoteService.getQuote(position)
             LoadResult.Page(
                response.results,
                if(position==1) null else position - 1,
                if(position==response.totalPages) null else position+1)

        }catch (ex: java.lang.Exception){
            ex.printStackTrace()
            LoadResult.Error(ex)
        }
    }

    /*
   *   @param - it will have the information that which page we want to load
   */
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {

        val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
        if (anchorPage?.prevKey != null) {
            return anchorPage?.prevKey!!.plus(1)
        } else if (anchorPage?.nextKey != null) {
            return anchorPage?.nextKey!!.minus(1)
        } else {
            return null // in this case load first page
        }

    }
}