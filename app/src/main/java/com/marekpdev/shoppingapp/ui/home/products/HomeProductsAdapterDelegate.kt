package com.marekpdev.shoppingapp.ui.home.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhHomeProductsBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder
import com.marekpdev.shoppingapp.ui.productvh.ProductHeightConstAdapterDelegate

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class HomeProductsAdapterDelegate(private val onProductClicked: (Product) -> Unit,
                                  private val onToggleFavourite: (Product) -> Unit) :
    BaseAdapterDelegate<HomeProducts, BaseViewHolder<VhHomeProductsBinding>>(HomeProducts::class.java){

    override fun bindViewHolder(item: HomeProducts, holder: BaseViewHolder<VhHomeProductsBinding>) {
        holder.bind {

            // Instead of creating adapter as a member variable and reusing it in bindViewHolder()
            // we need to create new instance every time we call bindViewHolder().
            // The problem is that if we have it as a member variable then
            // when we call 'adapter.replaceData(item.products)' then the same 'item.products' data
            // is shown in every ViewHolder that is being served by this HomeProductsAdapterDelegate
            // So basically when we scroll RecyclerView then every time we bind new data, the same
            // content (the one that has been most recently bound) is being shown in all ViewHolders
            val adapter = BaseAdapter(
                delegatesManager = AdapterDelegatesManager()
                    .addDelegate(ProductHeightConstAdapterDelegate(onProductClicked, onToggleFavourite))
            )

            rvHomeProducts.layoutManager = LinearLayoutManager(holder.context, LinearLayoutManager.HORIZONTAL, false)
            rvHomeProducts.adapter = adapter

            adapter.replaceData(item.products)
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhHomeProductsBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_home_products,
                parent,
                false
            )
        )
}