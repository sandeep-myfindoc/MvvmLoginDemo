package com.example.mvvmlogindemo.adapter

import android.R
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlogindemo.databinding.SubitemQuoteBinding
import com.example.mvvmlogindemo.modal.quoteResponse.Result
    class QuoteDataAdapter(private val quoteData: List<Result>) : RecyclerView.Adapter<QuoteDataAdapter.QuoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        var binding = SubitemQuoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        quoteData[position].image = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY500_CR0,0,352,500_AL_.jpg"
        holder.binding.ref = quoteData[position]
    }

    override fun getItemCount(): Int {
       return quoteData.size
    }
    // view holder
    inner class QuoteViewHolder(val binding: SubitemQuoteBinding): RecyclerView.ViewHolder(binding.root) {
    }
}
