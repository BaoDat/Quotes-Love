package com.mrtdev.quoteslove.ui.allquotes.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.mrtdev.quoteslove.database.models.Quote
import com.mrtdev.quoteslove.databinding.ViewQuoteBinding


class QuotesAdapter(
    val context: Context,
    lifecycleOwner: LifecycleOwner,
    private val data: LiveData<List<Quote>>
) : RecyclerView.Adapter<QuotesAdapter.ReviewViewHolder>() {

    init {
        data.observe(lifecycleOwner, Observer {
            notifyDataSetChanged()
        })
        setHasStableIds(true)
    }

    class ReviewViewHolder(
        val binding: ViewQuoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewQuoteBinding.inflate(inflater, parent, false)

        return ReviewViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.model = data.value?.getOrNull(position)

    }

    override fun getItemCount(): Int = data.value.orEmpty().size

}
