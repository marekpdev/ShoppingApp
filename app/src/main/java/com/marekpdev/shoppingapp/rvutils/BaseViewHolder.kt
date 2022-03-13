package com.marekpdev.shoppingapp.rvutils

import android.content.Context
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Base [RecyclerView.ViewHolder], commonly used with [AdapterDelegate].
 */
open class BaseViewHolder<T : ViewDataBinding>(private val binding: T) : RecyclerView.ViewHolder(binding.root) {

    /** Gets root view for this [RecyclerView.ViewHolder]. */
    val root: View
        get() = itemView.rootView

    /** Gets the [Context] for this [RecyclerView.ViewHolder]. */
    val context: Context
        get() = itemView.context

    /** Binding method that takes a binding function to perform. */
    fun bind(init: T.() -> Unit) = binding.init()
}

