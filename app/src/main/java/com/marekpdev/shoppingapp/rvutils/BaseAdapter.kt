package com.marekpdev.shoppingapp.rvutils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 * This class represents the most base [RecyclerView] adapter.
 *
 * This adapter should contain the necessary functionality for nearly all use cases as it accepts
 * a [Any] items and uses the [AdapterDelegatesManager] to provide [RecyclerView.ViewHolder]s and
 * binding.
 */
class BaseAdapter @JvmOverloads constructor(
    private val delegatesManager: AdapterDelegatesManager,
    private var items: MutableList<Any> = mutableListOf(),
    private var listener: BaseAdapterListener? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /** Returns the correct ViewType identifier, delegated to the [AdapterDelegatesManager]. */
    override fun getItemViewType(position: Int): Int =
        delegatesManager.getItemViewType(items, position)

    /** Returns a ViewHolder, delegated to the [AdapterDelegatesManager]. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegatesManager.onCreateViewHolder(parent, viewType)

    /** Binds a ViewHolder to a specific item, delegated to the [AdapterDelegatesManager]. */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegatesManager.onBindViewHolder(items, position, holder)

    /** Gets the number of items currently in the adapter. */
    override fun getItemCount(): Int = items.size

    override fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        delegatesManager.onViewRecycled(holder)

    override fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        delegatesManager.onFailedToRecycleView(holder)

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        delegatesManager.onViewAttachedToWindow(holder)

    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        delegatesManager.onViewDetachedFromWindow(holder)

    override fun getItemId(position: Int): Long = delegatesManager.getUniqueItemId(items, position)

    /** Gets the list of items currently in the adapter. */
    fun getItems(): List<Any> = items

    /**
     * Replaces the current adapter list with the given a list of given item and checks if the list
     * is going from empty to not-empty or vice-versa.
     *
     * Notifies data set changed if given [notify] boolean is true.
     */
    fun replaceData(newItem: Any, notify: Boolean = true) = replaceData(listOf(newItem), notify)

    /**
     * Replaces the current adapter list with the given list of items and checks if the list
     * is going from empty to not-empty or vice-versa.
     *
     * Notifies data set changed if given [notify] boolean is true.
     */
    fun replaceData(newItems: List<Any>, notify: Boolean = true) = synchronized(this) {
        val newSize = newItems.size
        val oldSize = items.size

        if (newSize < oldSize && oldSize == items.size) {
            notifyItemRangeRemoved(newSize, oldSize - newSize)
        }

        val diff = (items zip newItems).withIndex().filter { (_, pair) ->
            val (old, new) = pair
            old != new
        }

        val alreadyEmpty = items.isEmpty()

        items.clear()
        addData(newItems, notify = false)

        if (notify) {
            if (newSize > oldSize && newSize == items.size) {
                notifyItemRangeInserted(oldSize, newSize - oldSize)
            }
            diff.forEach { (index, _) -> notifyItemChanged(index) }
        }

        // Check if listener methods needs to be called
        listenerCheck(alreadyEmpty, newItems)
    }

    /**
     * Adds the given item to a list, which is added to the items to the current adapter list and
     * checks if the list is going from empty to not-empty or vice-versa.
     *
     * Notifies data set changed if given [notify] boolean is true.
     */
    fun addData(newItem: Any, notify: Boolean = true) = addData(listOf(newItem), notify)

    /**
     * Adds the given list of items to the current adapter list and checks if the list
     * is going from empty to not-empty or vice-versa.
     *
     * Notifies data set changed if given [notify] boolean is true.
     */
    fun addData(newItems: List<Any>, notify: Boolean = true) {
        val alreadyEmpty = items.isEmpty()

        items.addAll(newItems)
        if (notify) {
            notifyDataSetChanged() // This causes a new layout request
        }

        listenerCheck(alreadyEmpty, newItems)
    }

    /** Removes the items at the specifically given position and then notifies item removed. */
    fun onItemDismiss(position: Int) {
        val alreadyEmpty = items.isEmpty()

        items.removeAt(position)
        notifyItemRemoved(position)


        listenerCheck(alreadyEmpty, items)
    }

    /** Moves the item in [fromPosition] to [toPosition] and then notifies item moved. */
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    /** Performs a check on whether the adapter is going from empty to not-empty or vice-versa. */
    private fun listenerCheck(alreadyEmpty: Boolean, newItems: List<Any>) =
        listener?.let {
            when {
                !alreadyEmpty && newItems.isEmpty() -> it.listEmptied()
                !alreadyEmpty && newItems.isNotEmpty() -> it.listStillFilled()
                alreadyEmpty && newItems.isEmpty() -> it.listStillEmpty()
                alreadyEmpty && newItems.isNotEmpty() -> it.listFilled()
            }
        }

}

/**
 * Interface used to listen to whether the adapter is going from empty to not-empty or vice-versa.
 */
interface BaseAdapterListener {

    /** The is going from not-empty to empty. */
    fun listEmptied()

    /** The is going from empty to not-empty. */
    fun listFilled()

    /** The is still empty from empty state. */
    fun listStillEmpty()

    /** The is still filled from filled state. */
    fun listStillFilled()
}
