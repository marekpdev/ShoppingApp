package com.marekpdev.shoppingapp.ui.settings.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.VhSettingNotificationsBinding
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.rvutils.BaseAdapterDelegate
import com.marekpdev.shoppingapp.rvutils.BaseViewHolder

/**
 * Created by Marek Pszczolka on 01/03/2022.
 */
class NotificationsAdapterDelegate(private val onNotificationsToggle: (Setting.Notifications) -> Unit) :
    BaseAdapterDelegate<Setting.Notifications, BaseViewHolder<VhSettingNotificationsBinding>>(Setting.Notifications::class.java){

    override fun bindViewHolder(item: Setting.Notifications, holder: BaseViewHolder<VhSettingNotificationsBinding>) {
        holder.bind {
            cbSetting.setOnClickListener { onNotificationsToggle(item) }
            cbSetting.isChecked = item.enabled
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<VhSettingNotificationsBinding> =
        BaseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.vh_setting_notifications,
                parent,
                false
            )
        )
}