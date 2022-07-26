package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 13/03/2022.
 */

@HiltViewModel
class EditProfileViewModel @Inject constructor(editProfileStore: EditProfileStore): BaseViewModel<EditProfileState, EditProfileAction, EditProfileCommand>(editProfileStore)