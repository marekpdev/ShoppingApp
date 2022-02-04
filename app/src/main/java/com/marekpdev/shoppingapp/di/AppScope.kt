package com.marekpdev.shoppingapp.di

import javax.inject.Scope

/**
 * Created by Marek Pszczolka on 13/07/2021.
 *
 * Created custom AppScope to avoid misleading @Singleton annotation
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope