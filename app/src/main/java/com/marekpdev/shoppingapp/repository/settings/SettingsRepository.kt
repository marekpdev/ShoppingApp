package com.marekpdev.shoppingapp.repository.settings

import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Setting
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
interface SettingsRepository {

    suspend fun getSettings(): StateFlow<List<Setting>>

    suspend fun updateSetting(oldSetting: Setting, newSetting: Setting)
}