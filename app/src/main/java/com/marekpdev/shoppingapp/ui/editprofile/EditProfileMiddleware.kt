package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class EditProfileMiddleware @Inject constructor(private val userRepository: UserRepository) :
    Middleware<EditProfileState, EditProfileAction, EditProfileCommand> {

    override suspend fun bind(
        coroutineScope: CoroutineScope,
        state: StateFlow<EditProfileState>,
        requestAction: suspend (EditProfileAction) -> Unit
    ) {
        userRepository.getUser()
            .filterNotNull()
            .onEach { user -> requestAction(EditProfileAction.RefreshUserData(user)) }
            .launchIn(coroutineScope)
    }

    override suspend fun process(
        action: EditProfileAction,
        currentState: EditProfileState,
        requestAction: suspend (EditProfileAction) -> Unit,
        requestCommand: suspend (EditProfileCommand) -> Unit
    ) {
        when(action){
            is EditProfileAction.UpdateClicked -> onUpdateRequested(action, currentState, requestAction, requestCommand)
        }
    }

    private suspend fun onUpdateRequested(
        action: EditProfileAction.UpdateClicked,
        currentState: EditProfileState,
        requestAction: suspend (EditProfileAction) -> Unit,
        requestCommand: suspend (EditProfileCommand) -> Unit
    ) {
        requestAction(EditProfileAction.Loading)
        userRepository.updateUserData(currentState.name, currentState.surname)
        requestAction(EditProfileAction.ProfileUpdated)
    }
}