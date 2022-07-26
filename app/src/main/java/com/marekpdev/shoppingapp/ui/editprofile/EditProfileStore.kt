package com.marekpdev.shoppingapp.ui.editprofile

import com.marekpdev.shoppingapp.mvi.Middleware
import com.marekpdev.shoppingapp.mvi.Store

/**
 * Created by Marek Pszczolka on 26/07/2022.
 */
class EditProfileStore (
    initialState: EditProfileState,
    middlewares: List<Middleware<EditProfileState, EditProfileAction, EditProfileCommand>>,
    reducer: EditProfileReducer
) : Store<EditProfileState, EditProfileAction, EditProfileCommand>(initialState, middlewares, reducer)