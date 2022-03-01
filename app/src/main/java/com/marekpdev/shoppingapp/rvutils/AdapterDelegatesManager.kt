/*
 * Copyright (c) 2015 Hannes Dorfmann.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.marekpdev.shoppingapp.rvutils

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView


/**
 * This class is the element that ties {@link RecyclerView.Adapter} together with {@link
 * AdapterDelegate}.
 * <p>
 * So you have to add / register your {@link AdapterDelegate}s to this manager by calling {@link
 * #addDelegate(AdapterDelegate)}
 * </p>
 * <p>
 * <p>
 * Next you have to add this AdapterDelegatesManager to the {@link RecyclerView.Adapter} by calling
 * corresponding methods:
 * <ul>
 * <li> {@link #getItemViewType(List, int)}: Must be called from {@link
 * RecyclerView.Adapter#getItemViewType(int)}</li>
 * <li> {@link #onCreateViewHolder(ViewGroup, int)}: Must be called from {@link
 * RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}</li>
 * <li> {@link #onBindViewHolder(List, int, RecyclerView.ViewHolder)}: Must be called from {@link
 * RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}</li>
 * </ul>
 * <p>
 * You can also set a fallback {@link AdapterDelegate} by using {@link
 * #setFallbackDelegate(AdapterDelegate)} that will be used if no {@link AdapterDelegate} is
 * responsible to handle a certain view type. If no fallback is specified, an Exception will be
 * thrown if no {@link AdapterDelegate} is responsible to handle a certain view type
 * </p>
 *
 * @author Hannes Dorfmann
 * @author bam
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class AdapterDelegatesManager(private var fallbackDelegate: AdapterDelegate<out RecyclerView.ViewHolder> = FallbackAdapterDelegate()) {


    /** Map for ViewType to AdapterDelegate */
    var delegates = SparseArrayCompat<AdapterDelegate<out RecyclerView.ViewHolder>>()

    /** Adds an [AdapterDelegate]. */
    fun addDelegate(delegate: AdapterDelegate<out RecyclerView.ViewHolder>): AdapterDelegatesManager {

        var viewType = delegates.size()
        while (delegates.get(viewType) != null) viewType++

        return addDelegate(viewType, false, delegate)
    }

    /** Adds an [AdapterDelegate] with a boolean allowing replacement and the specific ViewType. */
    fun addDelegate(
        viewType: Int,
        allowReplacingDelegate: Boolean = false,
        delegate: AdapterDelegate<out RecyclerView.ViewHolder>
    ): AdapterDelegatesManager {
        if (viewType == FALLBACK_DELEGATE_VIEW_TYPE) {
            throw IllegalArgumentException("The view type = $FALLBACK_DELEGATE_VIEW_TYPE is reserved for fallback adapter delegate (see setFallbackDelegate() ). Please use another view type.")
        }

        if (!allowReplacingDelegate && delegates.get(viewType) != null) {
            throw IllegalArgumentException("An AdapterDelegate is already registered for the viewType = $viewType. Already registered AdapterDelegate is delegates.get(viewType)")
        }

        delegates.put(viewType, delegate)

        return this
    }

    /** Remove the given [AdapterDelegate]. */
    fun removeDelegate(delegate: AdapterDelegate<out RecyclerView.ViewHolder>): AdapterDelegatesManager {
        delegates.indexOfValue(delegate)
            .takeIf { it >= 0 }
            ?.also { delegates.removeAt(it) }

        return this
    }

    /** Remove the [AdapterDelegate] from the [delegates] that matches the given [viewType]. */
    fun removeDelegate(viewType: Int): AdapterDelegatesManager {
        delegates.remove(viewType)
        return this
    }

    /** Searches all the registered [AdapterDelegate] and associated ViewType integer.*/
    fun getItemViewType(items: List<Any>, position: Int): Int {
        for (i in 0 until delegates.size()) {
            val delegate = delegates.valueAt(i)
            if (delegate.isForViewType(items, position)) {
                return delegates.keyAt(i)
            }
        }

        return FALLBACK_DELEGATE_VIEW_TYPE
    }

    fun getViewType(delegate: AdapterDelegate<*>): Int {
        return delegates.indexOfValue(delegate)
            .takeIf { it != INVALID_VIEW_TYPE }
            ?.let { delegates.keyAt(it) }
            ?: INVALID_VIEW_TYPE
    }

    fun getUniqueItemId(items: List<Any>, pos: Int): Long =
        getDelegateForViewType(getItemViewType(items, pos)).getUniqueItemId(items, pos)

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getDelegateForViewType(viewType).let { return it.onCreateViewHolder(parent) }

    fun onBindViewHolder(items: List<Any>, pos: Int, viewHolder: RecyclerView.ViewHolder) =
        getDelegateForViewType(viewHolder.itemViewType).onBindViewHolder(items, pos, viewHolder)

    fun onViewRecycled(holder: RecyclerView.ViewHolder) =
        getDelegateForViewType(holder.itemViewType).onViewRecycled(holder)

    fun onFailedToRecycleView(holder: RecyclerView.ViewHolder): Boolean =
        getDelegateForViewType(holder.itemViewType).onFailedToRecycleView(holder)

    fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) =
        getDelegateForViewType(holder.itemViewType).onViewAttachedToWindow(holder)

    fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) =
        getDelegateForViewType(holder.itemViewType).onViewDetachedFromWindow(holder)

    fun setFallbackDelegate(fallbackDelegate: AdapterDelegate<*>): AdapterDelegatesManager {
        this.fallbackDelegate = fallbackDelegate
        return this
    }

    fun getDelegateForViewType(viewType: Int): AdapterDelegate<RecyclerView.ViewHolder> {
        val delegate = delegates.get(viewType) ?: fallbackDelegate

        @Suppress("UNCHECKED_CAST")
        return delegate as AdapterDelegate<RecyclerView.ViewHolder>
    }

    fun getFallbackDelegate(): AdapterDelegate<out RecyclerView.ViewHolder> = fallbackDelegate


    companion object {
        /** This id is used internally to claim that the [] */
        private const val FALLBACK_DELEGATE_VIEW_TYPE = Integer.MAX_VALUE - 1

        private const val INVALID_VIEW_TYPE = -1
    }
}