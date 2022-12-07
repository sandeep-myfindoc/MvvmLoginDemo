package com.example.mvvmlogindemo.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.example.mvvmlogindemo.viewModel.MainActivityViewModel
@ExperimentalPagingApi
class MainViewModelFactory(private val quoteRepositry: QuoteRepositry):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(quoteRepositry) as T
    }
}