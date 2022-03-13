package com.marekpdev.shoppingapp.rvutils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhFallbackBinding

class FallbackAdapterDelegate : BaseAdapterDelegate<Any, BaseViewHolder<VhFallbackBinding>>(Any::class.java) {

    override fun bindViewHolder(item: Any, holder: BaseViewHolder<VhFallbackBinding>) = Unit

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhFallbackBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_fallback,
                parent,
                false
            )
        )
}
