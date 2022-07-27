package com.marekpdev.shoppingapp.ui.login

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentLoginBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginState, LoginAction, LoginCommand, FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewModel by viewModels<LoginViewModel>()

    override fun initLayout(binding: FragmentLoginBinding) = with(binding) {
        etEmail.doAfterTextChanged {
            viewModel.dispatch(LoginAction.InputChanged(etEmail.text.toString(), etPassword.text.toString()))
        }

        etPassword.doAfterTextChanged {
            viewModel.dispatch(LoginAction.InputChanged(etEmail.text.toString(), etPassword.text.toString()))
        }

        btnLogin.setOnClickListener {
            viewModel.dispatch(LoginAction.RequestLogin(etEmail.text.toString(), etPassword.text.toString()))
        }
        btnRegister.setOnClickListener {
            viewModel.dispatch(LoginAction.RegisterClicked)
        }

        // todo only for testing
        viewModel.dispatch(LoginAction.InputChanged("test@test.com", "password"))
    }

    override fun render(state: LoginState) {
        binding.apply {
            etEmail.setTextIfDifferent(state.email)
            etPassword.setTextIfDifferent(state.password)
            tvError.setTextIfDifferent(state.error)
            pbLogin.visibility = if(state.loading) View.VISIBLE else View.GONE
        }
    }

    override fun onCommand(command: LoginCommand) {
        when(command){
            is LoginCommand.GoToAccountScreen -> {
                findNavController().navigate(R.id.action_loginFragment_to_accountFragment)
            }
            is LoginCommand.GoToRegistrationScreen -> {
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            }
        }
    }
}