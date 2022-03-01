package com.marekpdev.shoppingapp.ui.home.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeShowMoreBinding
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeShowMoreAdapterDelegate(private val onShowMoreClicked: (String) -> Unit) :
    BaseAdapterDelegate<String, BaseViewHolder<VhHomeShowMoreBinding>>(String::class.java){

    override fun bindViewHolder(item: String, holder: BaseViewHolder<VhHomeShowMoreBinding>) {
        holder.bind {
            root.setOnClickListener { onShowMoreClicked(item) }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhHomeShowMoreBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_home_show_more,
                parent,
                false
            )
        )
}