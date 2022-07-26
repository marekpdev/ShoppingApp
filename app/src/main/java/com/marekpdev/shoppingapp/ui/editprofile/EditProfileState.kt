package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
data class EditProfileState(
    val email: String,
    val name: String,
    val surname: String,
    val loading: Boolean
): State