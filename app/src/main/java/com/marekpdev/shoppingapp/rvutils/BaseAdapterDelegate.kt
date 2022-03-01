package com.marekpdev.shoppingapp.rvutils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * The base class for all [RecyclerView] adapters that wish to use the 'Delegate' pattern.
 *
 * Any subclass simply needs to provide a [RecyclerView.ViewHolder] implementation, commonly a
 * subclass of [BaseViewHolder], and override the create and bind functions.
 */
abstract class BaseAdapterDelegate<in M, VH : RecyclerView.ViewHolder>(private val modelClass: Class<out M>) : AdapterDelegate<VH>() {

    /**
     * Determines if the an item at the given position in the given list is assignable from
     * [modelClass] of this class. This will indicate whether this delegate supports that item type.
     */
    override fun isForViewType(items: List<Any>, position: Int): Boolean = modelClass.isAssignableFrom(items[position].javaClass)

    /** Creates the [RecyclerView.ViewHolder] for the given data source item. */
    override fun onCreateViewHolder(parent: ViewGroup): VH = createViewHolder(parent)

    /** Called to bind the [RecyclerView.ViewHolder] to the item of the data source set. */
    override fun onBindViewHolder(items: List<Any>, position: Int, holder: VH) = bindViewHolder(getItem(items, position), holder)

    /** Implementing this function because the given list contains all Adapter items and we only want the [AdapterDelegate] taking care of its own items. */
    override fun getUniqueItemId(items: List<Any>, position: Int): Long = getItemUniqueID(getItem(items, position))

    /** Perform the actual binding between the [item] and the [holder]. */
    protected abstract fun bindViewHolder(item: M, holder: VH)

    /** Creates the actual [RecyclerView.ViewHolder], of the specified delegate type. */
    protected abstract fun createViewHolder(parent: ViewGroup): VH

    /** Get a Unique ID for the specific item in the list. */
    protected open fun getItemUniqueID(item: M): Long = item.hashCode().toLong()

    private fun getItem(items: List<Any>, position: Int): M =
        modelClass.cast(items[position]) ?: throw java.lang.IllegalStateException("Casting for item at position $position cannot be performed!")
}
