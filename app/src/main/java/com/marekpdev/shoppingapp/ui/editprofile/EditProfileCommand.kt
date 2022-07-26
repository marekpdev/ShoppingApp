package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.Command

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
sealed class EditProfileCommand: Command {

    object GoBackToAccountScreen: EditProfileCommand()
}