package com.example.mvvmlogindemo.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlogindemo.data.quoteData.QuoteList
import com.example.mvvmlogindemo.repo.QuoteRepositry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repository: QuoteRepositry): ViewModel(){
  var quotes = MutableLiveData<QuoteList>()
    init {

    }

    fun getQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            quotes.postValue(repository.getQoutes(1))
        }
    }
}