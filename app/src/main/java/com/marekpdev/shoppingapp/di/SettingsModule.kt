package com.marekpdev.shoppingapp.di

import com.marekpdev.shoppingapp.repository.settings.SettingsRepository
import com.marekpdev.shoppingapp.repository.settings.SettingsRepositoryImpl
import com.marekpdev.shoppingapp.ui.settings.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Marek Pszczolka on 12/07/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Provides
    @Singleton
    fun provideSettingsMiddleware(settingsRepository: SettingsRepository) = SettingsMiddleware(settingsRepository)

    @Provides
    @Singleton
    fun provideSettingsNavigationMiddleware() = SettingsNavigationMiddleware()

    @Provides
    @Singleton
    fun provideSettingsStore(
        settingsMiddleware: SettingsMiddleware,
        settingsNavigationMiddleware: SettingsNavigationMiddleware
    ): SettingsStore {
        return SettingsStore(
            SettingsState(emptyList(), false),
            listOf(
                settingsMiddleware,
                settingsNavigationMiddleware
            ),
            SettingsReducer()
        )
    }

}

@Module
@InstallIn(SingletonComponent::class)
interface SettingsDependencyBinder {

    @Binds
    @Singleton
    fun bindSettingsRepository(repositoryImpl: SettingsRepositoryImpl): SettingsRepository

}

