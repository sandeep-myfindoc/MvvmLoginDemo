package com.example.mvvmlogindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.adapter.QuoteDataAdapter
import com.example.mvvmlogindemo.database.QuoteDataBase
import com.example.mvvmlogindemo.databinding.ActivityHomeBinding
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.NetworkResult
import com.example.mvvmlogindemo.network.RetrofitHelper
import com.example.mvvmlogindemo.paging.quote.LoaderAdapter
import com.example.mvvmlogindemo.paging.quote.QuotePagingAdapter
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.example.mvvmlogindemo.viewModel.MainActivityViewModel
import com.example.mvvmlogindemo.viewModelFactory.MainViewModelFactory
@ExperimentalPagingApi
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: QuoteDataAdapter
    private lateinit var pagingAdapter:QuotePagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,  R.layout.activity_home)
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)
        val apiService = RetrofitHelper.getClient().create(ApiService::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(QuoteRepositry(apiService,
            QuoteDataBase.getQuoteDatabase(this)))).get(MainActivityViewModel::class.java)
        useSimplaAdapter()
        //usePagingAdapter()
    }

    private fun usePagingAdapter(){
        pagingAdapter =QuotePagingAdapter()
        binding.rvNotes.adapter = pagingAdapter.withLoadStateHeaderAndFooter(header = LoaderAdapter(),
        footer = LoaderAdapter())
        /*viewModel.list.observe(this, Observer {
            pagingAdapter.submitData(lifecycle,it)
        })*/
    }

    private fun useSimplaAdapter(){
        viewModel.quoteResponse.observe(this, Observer {
            when(it){
                is NetworkResult.Loading->{
                    Log.e("Inside","Home Loading");
                    binding.progressBar.visibility = View.VISIBLE
                }
                is NetworkResult.Sucess->{
                    Log.e("Inside","Home Success"+it.data?.results);
                    adapter = QuoteDataAdapter(it.data?.results!!)
                    binding.rvNotes.adapter = adapter
                    binding.progressBar.visibility = View.GONE
                }
                is NetworkResult.Error->{
                    Log.e("Inside","Home Error"+it.errorMessage.toString());
                    binding.progressBar.visibility = View.GONE
                }
            }
        })
    }
}