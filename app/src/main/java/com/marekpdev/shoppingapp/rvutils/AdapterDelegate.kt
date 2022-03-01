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
import androidx.recyclerview.widget.RecyclerView

/**
 * This delegate provide method to hook in this delegate to [RecyclerView.Adapter] lifecycle.
 * This "hook in" mechanism is provided by [AdapterDelegatesManager] and that is the
 * component you have to use.
 *
 * @author Hannes Dorfmann
 * @author bam
 * @since 1.0
 */
@Suppress("UNUSED_PARAMETER")
abstract class AdapterDelegate<VH : RecyclerView.ViewHolder> {

    /**
     * Called to determine whether this AdapterDelegate is the responsible for the given data
     * element.
     *
     * @param items    The data source of the Adapter
     * @param position The position in the datasource
     * @return true, if this item is responsible,  otherwise false
     */
    abstract fun isForViewType(items: List<Any>, position: Int): Boolean

    /**
     * Creates the  [RecyclerView.ViewHolder] for the given data source item
     *
     * @param parent The ViewGroup parent of the given datasource
     * @return The new instantiated [RecyclerView.ViewHolder]
     */
    abstract fun onCreateViewHolder(parent: ViewGroup): VH

    /**
     * Called to bind the [RecyclerView.ViewHolder] to the item of the data source set
     *
     * @param items    The data source
     * @param position The position in the datasource
     * @param holder   The [RecyclerView.ViewHolder] to bind
     */
    abstract fun onBindViewHolder(items: List<Any>, position: Int, holder: VH)

    /**
     * Return the stable ID for the item at <code>position</code>. If {@link #hasStableIds()}
     * would return false this method should return {@link #NO_ID}. The default implementation
     * of this method returns {@link #NO_ID}.
     *
     * @param position Adapter position to query
     * @return the stable ID of the item at position
     */
    abstract fun getUniqueItemId(items: List<Any>, position: Int): Long

    /**
     * Called when a view created by this adapter has been recycled.
     *
     * A view is recycled when a [RecyclerView.LayoutManager] decides that it no longer
     * needs to be attached to its parent [RecyclerView]. This can be because it has
     * fallen out of visibility or a set of cached views represented by views still
     * attached to the parent RecyclerView. If an item view has large or expensive data
     * bound to it such as large bitmaps, this may be a good place to release those
     * resources.
     *
     *
     * RecyclerView calls this method right before clearing ViewHolder's internal data and
     * sending it to RecycledViewPool. This way, if ViewHolder was holding valid information
     * before being recycled, you can call [RecyclerView.ViewHolder.getAdapterPosition] to
     * get its adapter position.
     *
     * @param viewHolder The ViewHolder for the view being recycled
     */
    open fun onViewRecycled(viewHolder: VH) {}

    /**
     * Called by the RecyclerView if a ViewHolder created by this Adapter cannot be recycled
     * due to its transient state. Upon receiving this callback, Adapter can clear the
     * animation(s) that effect the View's transient state and return `true` so that
     * the View can be recycled. Keep in mind that the View in question is already removed from
     * the RecyclerView.
     *
     *
     * In some cases, it is acceptable to recycle a View although it has transient state. Most
     * of the time, this is a case where the transient state will be cleared in
     * [RecyclerView.Adapter.onBindViewHolder] call when View is
     * rebound to a new position.
     * For this reason, RecyclerView leaves the decision to the Adapter and uses the return
     * value of this method to decide whether the View should be recycled or not.
     *
     *
     * Note that when all animations are created by [RecyclerView.ItemAnimator], you
     * should never receive this callback because RecyclerView keeps those Views as children
     * until their animations are complete. This callback is useful when children of the item
     * views create animations which may not be easy to implement using an [ ].
     *
     *
     * You should *never* fix this issue by calling
     * `holder.itemView.setHasTransientState(false);` unless you've previously called
     * `holder.itemView.setHasTransientState(true);`. Each
     * `View.setHasTransientState(true)` call must be matched by a
     * `View.setHasTransientState(false)` call, otherwise, the state of the View
     * may become inconsistent. You should always prefer to end or cancel animations that are
     * triggering the transient state instead of handling it manually.
     *
     * @param holder The ViewHolder containing the View that could not be recycled due to its
     * transient state.
     * @return True if the View should be recycled, false otherwise. Note that if this method
     * returns `true`, RecyclerView *will ignore* the transient state of
     * the View and recycle it regardless. If this method returns `false`,
     * RecyclerView will check the View's transient state again before giving a final decision.
     * Default implementation returns false.
     */
    open fun onFailedToRecycleView(holder: VH): Boolean {
        return false
    }

    /**
     * Called when a view created by this adapter has been attached to a window.
     *
     * This can be used as a reasonable signal that the view is about to be seen
     * by the user. If the adapter previously freed any resources in
     * [ onViewDetachedFromWindow][RecyclerView.Adapter.onViewDetachedFromWindow]
     * those resources should be restored here.
     *
     * @param holder Holder of the view being attached
     */
    open fun onViewAttachedToWindow(holder: VH) {}

    /**
     * Called when a view created by this adapter has been detached from its window.
     *
     * Becoming detached from the window is not necessarily a permanent condition;
     * the consumer of an Adapter's views may choose to cache views offscreen while they
     * are not visible, attaching an detaching them as appropriate.
     *
     * @param holder Holder of the view being detached
     */
    open fun onViewDetachedFromWindow(holder: VH) {}
}
