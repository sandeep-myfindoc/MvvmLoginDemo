package com.example.mvvmlogindemo.paging.quote

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmlogindemo.databinding.SubitemQuoteBinding
import com.example.mvvmlogindemo.modal.quoteResponse.Result
class QuotePagingAdapter : PagingDataAdapter<Result,QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding =
            SubitemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        //getItem(position)?.image = "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_QL50_SY500_CR0,0,352,500_AL_.jpg"
        holder.binding.ref = getItem(position)
    }
    inner class QuoteViewHolder(val binding: SubitemQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }

    }
}