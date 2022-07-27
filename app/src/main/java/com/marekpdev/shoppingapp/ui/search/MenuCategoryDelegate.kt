package com.marekpdev.shoppingapp.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhMenuCategoryBinding
import com.marekpdev.shoppingapp.models.Category
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class MenuCategoryDelegate(private val onCategoryClicked: (Category) -> Unit) :
    BaseAdapterDelegate<Category, BaseViewHolder<VhMenuCategoryBinding>>(Category::class.java){

    override fun bindViewHolder(item: Category, holder: BaseViewHolder<VhMenuCategoryBinding>) {
        holder.bind {
            root.setOnClickListener { onCategoryClicked(item) }
            tvCategoryName.text = item.name
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhMenuCategoryBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_menu_category,
                parent,
                false
            )
        )
}