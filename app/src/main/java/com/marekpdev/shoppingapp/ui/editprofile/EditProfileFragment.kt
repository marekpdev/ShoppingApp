package com.marekpdev.shoppingapp.ui.editprofile

import androidx.fragment.app.viewModels
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentEditProfileBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class EditProfileFragment : BaseFragment<EditProfileState, EditProfileAction, EditProfileCommand, FragmentEditProfileBinding>(R.layout.fragment_edit_profile) {

    override val viewModel by viewModels<EditProfileViewModel>()

    override fun initLayout(binding: FragmentEditProfileBinding) = with(binding) {
        btnUpdate.setOnClickListener { viewModel.dispatch(EditProfileAction.UpdateClicked) }
    }

    override fun render(state: EditProfileState) {
        binding.apply {
            tvEmail.setTextIfDifferent(state.email)
            etName.setTextIfDifferent(state.name)
            etSurname.setTextIfDifferent(state.surname)
        }
    }

    override fun onCommand(command: EditProfileCommand) {
        when(command) {
            EditProfileCommand.GoBackToAccountScreen -> {
                // TODO
            }
        }
    }
}