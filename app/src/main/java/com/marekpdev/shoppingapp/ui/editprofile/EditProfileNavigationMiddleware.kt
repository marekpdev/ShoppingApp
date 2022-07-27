package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.Middleware
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class EditProfileNavigationMiddleware @Inject constructor() :
    Middleware<EditProfileState, EditProfileAction, EditProfileCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<EditProfileState>,
        requestAction: suspend (EditProfileAction) -> Unit
    ) {

    }

    override suspend fun process(
        action: EditProfileAction,
        currentState: EditProfileState,
        requestAction: suspend (EditProfileAction) -> Unit,
        requestCommand: suspend (EditProfileCommand) -> Unit
    ) {
        when(action){
            is EditProfileAction.ProfileUpdated -> requestCommand(EditProfileCommand.GoBackToAccountScreen)
        }
    }

}