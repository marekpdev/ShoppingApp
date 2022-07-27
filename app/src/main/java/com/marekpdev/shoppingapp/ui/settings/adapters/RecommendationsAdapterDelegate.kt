package com.marekpdev.shoppingapp.ui.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhAddressBinding
import com.marekpdev.shoppingapp.databinding.VhSettingNotificationsBinding
import com.marekpdev.shoppingapp.databinding.VhSettingRecommendationsBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class RecommendationsAdapterDelegate(private val onRecommendationsToggle: (Setting.Recommendations) -> Unit) :
    BaseAdapterDelegate<Setting.Recommendations, BaseViewHolder<VhSettingRecommendationsBinding>>(Setting.Recommendations::class.java){

    override fun bindViewHolder(item: Setting.Recommendations, holder: BaseViewHolder<VhSettingRecommendationsBinding>) {
        holder.bind {
            cbSetting.setOnClickListener { onRecommendationsToggle(item) }
            cbSetting.isChecked = item.enabled
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhSettingRecommendationsBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_setting_recommendations,
                parent,
                false
            )
        )
}