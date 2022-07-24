package com.marekpdev.shoppingapp.ui.home.banner.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhBannerImageBinding
import com.marekpdev.shoppingapp.models.HomeBanner
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class BannerImageAdapterDelegate :
    BaseAdapterDelegate<HomeBanner, BaseViewHolder<VhBannerImageBinding>>(HomeBanner::class.java){

    override fun bindViewHolder(item: HomeBanner, holder: BaseViewHolder<VhBannerImageBinding>) {
        holder.bind {
            ivBannerImage.setImageResource(item.imageResId)
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhBannerImageBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_banner_image,
                parent,
                false
            )
        )
}