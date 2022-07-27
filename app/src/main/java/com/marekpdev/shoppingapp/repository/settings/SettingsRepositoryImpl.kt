package com.marekpdev.shoppingapp.repository.settings

import com.marekpdev.shoppingapp.models.Setting
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class SettingsRepositoryImpl @Inject constructor(): SettingsRepository{

    private val settings = MutableStateFlow(
        listOf<Setting>(
            Setting.Notifications(false),
            Setting.Recommendations(false)
        )
    )

    override suspend fun getSettings(): StateFlow<List<Setting>> {
        delay(1000L) // todo only for testing
        return settings
    }

    override suspend fun updateSetting(oldSetting: Setting, newSetting: Setting) {
        val indexOf = settings.value.indexOf(oldSetting)
        if(indexOf >= 0){
            val updatedSettings = settings.value.toMutableList()
            updatedSettings[indexOf] = newSetting
            settings.emit(updatedSettings)
        }
    }

}