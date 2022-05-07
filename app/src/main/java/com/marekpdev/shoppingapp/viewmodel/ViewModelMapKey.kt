package com.marekpdev.shoppingapp.viewmodel

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation indicating the provider of a `ViewModel` of a given type.
 *
 * The annotated provider function will later be used by [ViewModelFactory] to create instances
 * of specific `ViewModel`s.
 *
 * Example usage:
 * ```
 * @Binds
 * @IntoMap
 * @ViewModelMapKey(MyViewModel::class)
 * fun bindUserViewModel(viewModel: MyViewModel): ViewModel
 * ```
 *
 * @property value Type of the `ViewModel`.
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@MapKey
annotation class ViewModelMapKey(val value: KClass<out ViewModel>)
