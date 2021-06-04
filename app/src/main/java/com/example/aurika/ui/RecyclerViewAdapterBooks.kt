package com.example.aurika.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aurika.databinding.ListItemBinding

class RecyclerViewAdapterBooks(val clickListener: BookClickListener) : ListAdapter<BookDomain, RecyclerViewAdapterBooks.BookViewHolder>(bookDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterBooks.BookViewHolder {

       return BookViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: RecyclerViewAdapterBooks.BookViewHolder, position: Int) {

        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class BookViewHolder private constructor(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            item: BookDomain,
            clickListener: BookClickListener
        ){

            binding.clickListener = clickListener
            binding.book = item
            binding.executePendingBindings()

        }

        companion object {

            fun from(parent: ViewGroup): BookViewHolder{

                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(inflater, parent, false)

                return BookViewHolder(binding)
        }}
    }

}

class BookClickListener(val clickListener: (book: BookDomain) -> Unit ){

    fun onClick(book: BookDomain) = clickListener(book)
}


class bookDiffCallback : DiffUtil.ItemCallback<BookDomain>() {
    override fun areItemsTheSame(oldItem: BookDomain, newItem: BookDomain): Boolean {

        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookDomain, newItem: BookDomain): Boolean {
        return oldItem == newItem
    }
}