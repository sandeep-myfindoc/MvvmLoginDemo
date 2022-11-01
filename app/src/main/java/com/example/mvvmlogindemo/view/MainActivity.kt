package com.example.mvvmlogindemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.R
import com.example.mvvmlogindemo.databinding.ActivityMainBinding
import com.example.mvvmlogindemo.network.ApiService
import com.example.mvvmlogindemo.network.RetrofitHelper
import com.example.mvvmlogindemo.repo.QuoteRepositry
import com.example.mvvmlogindemo.viewModel.MainActivityViewModel
import com.example.mvvmlogindemo.viewModelFactory.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,  R.layout.activity_main)
        val apiService = RetrofitHelper.getClient().create(ApiService::class.java)
        viewModel = ViewModelProvider(this, MainViewModelFactory(QuoteRepositry(apiService))).get(MainActivityViewModel::class.java)
        viewModel.quotes.observe(this, Observer {
            Log.e("Total Quotes: ",it.count.toString())
            Log.e("Result: ",it.results.toString())
        })
        binding.btn.setOnClickListener {
            viewModel.getQuotes()
        }
    }
}