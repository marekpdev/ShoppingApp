package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.models.User
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.ui.login.LoginAction

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class EditProfileAction : Action {

    data class InputChanged(val name: String, val surname: String): EditProfileAction()

    object UpdateClicked: EditProfileAction()
    object Loading: EditProfileAction()
    object ProfileUpdated: EditProfileAction()

    data class RefreshUserData(val user: User): EditProfileAction()

}