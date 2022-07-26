package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.Reducer

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class EditProfileReducer: Reducer<EditProfileState, EditProfileAction> {

    override fun reduce(currentState: EditProfileState, action: EditProfileAction): EditProfileState {
        return when (action){
            is EditProfileAction.InputChanged -> {
                currentState.copy(
                    name = action.name,
                    surname = action.surname
                )
            }
            is EditProfileAction.Loading -> {
                currentState.copy(loading = true)
            }
            is EditProfileAction.RefreshUserData -> {
                currentState.copy(
                    email = action.user.email,
                    name = action.user.name,
                    surname = action.user.surname,
                    loading = false
                )
            }
            is EditProfileAction.ProfileUpdated -> {
                currentState.copy(loading = false)
            }
            else -> {
                currentState
            }
        }
    }

}