package com.example.mvvmlogindemo.paging.quote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.mvvmlogindemo.database.QuoteDataBase
import com.example.mvvmlogindemo.database.quote.QuoteRemoteKeys
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.modal.quoteResponse.Result
//https://www.youtube.com/watch?v=NiO3GrCsgdk
@ExperimentalPagingApi
class QuoteRemoteMediator(
    private val quoApiService: ApiService,
    private val dataBase: QuoteDataBase
): RemoteMediator<Int,Result>() {
    private val quoteDao = dataBase.getQuoteDao()
    private val quoteRemoteKeysDao = dataBase.getQuoteRemoteDao()
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Result>): MediatorResult {
        // Fetch Quote API
        //Save data to DB
        //Logic for state: Refresh, Prepend, Append
        try{
            val currentPage = when(loadType){
                LoadType.REFRESH->{
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1)?:1
                }
                LoadType.PREPEND->{
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevKey?:return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                    prevPage
                }
                LoadType.APPEND->{
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextKey?:return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                    nextPage
                }
            }
            val response = quoApiService.getQuote(currentPage)
            val endOfPaginationReached = response.totalPages == currentPage
            val prevPage = if(currentPage == 1) null else currentPage-1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1
            dataBase.withTransaction {
                if(loadType == LoadType.REFRESH){
                    quoteDao.deleteQuotes()
                    quoteRemoteKeysDao.deleteAllRemoteKey()
                }
                quoteDao.addQuotes(response.results)
                val keys = response.results.map {quote->
                    QuoteRemoteKeys(id = quote._id,
                    prevKey = prevPage,
                    nextKey = nextPage)
                }
                quoteRemoteKeysDao.addAllRemoteKey(keys)
            }
            return MediatorResult.Success(endOfPaginationReached)
        }catch(ex:Exception){
            ex.printStackTrace()
            return MediatorResult.Error(ex)
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?._id?.let { id ->
                quoteRemoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { quote ->
                quoteRemoteKeysDao.getRemoteKey(id = quote._id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Result>
    ): QuoteRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { quote ->
                quoteRemoteKeysDao.getRemoteKey(id = quote._id)
            }
    }
}