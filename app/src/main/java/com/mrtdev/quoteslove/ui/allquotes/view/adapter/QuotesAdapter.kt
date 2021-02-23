package com.mrtdev.quoteslove.ui.allquotes.view.adapter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.mrtdev.quoteslove.base.extensions.drawableRes
import com.mrtdev.quoteslove.database.models.Quote
import com.mrtdev.quoteslove.databinding.ViewQuoteBinding
import com.mrtdev.quoteslove.domain.quotes.models.QuotesAvatar


class QuotesAdapter(
    private val context: Context,
    lifecycleOwner: LifecycleOwner,
    private val data: LiveData<List<Quote>>,
    private val onDataChanged: (Boolean) -> Unit
) : RecyclerView.Adapter<QuotesAdapter.ReviewViewHolder>() {

    private val listImages = QuotesAvatar.values().toList()
    private val drawableId = MutableLiveData<Int>()

    init {
        setHasStableIds(true)
        data.observe(lifecycleOwner, {
            notifyDataSetChanged()
            onDataChanged(it.isEmpty())
        })
    }

    class ReviewViewHolder(
        val binding: ViewQuoteBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewQuoteBinding.inflate(inflater, parent, false)


        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.binding.model = data.value?.getOrNull(position)

        holder.binding.ivContent.setImageResource(getImages())

        holder.binding.ivCopy.setOnClickListener {
            val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("text", holder.binding.tvThinh.text)
            clipboardManager.setPrimaryClip(clipData)
        }

    }

    override fun getItemCount() = data.value?.size ?: 0

    override fun getItemId(position: Int): Long = data.value?.getOrNull(position)!!.id

    private fun getImages(): Int {
        val listImagesShuffled = listImages.shuffled()
        return listImagesShuffled[drawableId.value ?: 0].drawableRes
    }
}
