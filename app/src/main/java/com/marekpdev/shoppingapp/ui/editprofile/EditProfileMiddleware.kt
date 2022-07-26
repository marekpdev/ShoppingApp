package com.marekpdev.shoppingapp.ui.editprofile

import android.util.Log
import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.repository.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
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
        coroutineScope.launch {
            userRepository.getUser()
                .collectLatest { user ->
                    user?.let {
                        requestAction(EditProfileAction.RefreshUserData(it))
                    }
                }
        }
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
        Log.d("FEO33", "Current name ${currentState.name}")
        userRepository.updateUserData(currentState.name, currentState.surname)
        requestAction(EditProfileAction.ProfileUpdated)
    }
}