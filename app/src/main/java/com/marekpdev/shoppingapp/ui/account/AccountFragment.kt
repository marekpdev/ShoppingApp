package com.marekpdev.shoppingapp.ui.account

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAccountBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class AccountFragment : BaseFragment<AccountState, AccountAction, AccountCommand, FragmentAccountBinding>(R.layout.fragment_account) {

    override val viewModel by viewModels<AccountViewModel>()

    override fun initLayout(binding: FragmentAccountBinding) = with(binding){
        btnEditProfile.setOnClickListener { viewModel.dispatch(AccountAction.EditProfileClicked) }
        btnOrders.setOnClickListener { viewModel.dispatch(AccountAction.OrdersClicked) }
        btnAddresses.setOnClickListener { viewModel.dispatch(AccountAction.AddressesClicked) }
        btnPaymentMethods.setOnClickListener { viewModel.dispatch(AccountAction.PaymentMethodsClicked) }
        btnSettings.setOnClickListener { viewModel.dispatch(AccountAction.SettingsClicked) }
        btnLogout.setOnClickListener { viewModel.dispatch(AccountAction.LogoutClicked) }
    }

    override fun render(state: AccountState) {
        binding.apply {
            userLabel.text = state.email
        }
    }

    override fun onCommand(command: AccountCommand) {
        when(command){
            AccountCommand.GoToEditProfileScreen -> { findNavController().navigate(R.id.action_accountFragment_to_editProfileFragment) }
            AccountCommand.GoToOrdersScreen -> { findNavController().navigate(R.id.action_accountFragment_to_ordersFragment) }
            AccountCommand.GoToAddressesScreen -> { findNavController().navigate(R.id.action_accountFragment_to_addressesFragment) }
            AccountCommand.GoToPaymentMethodsScreen -> { findNavController().navigate(R.id.action_accountFragment_to_paymentMethodsFragment) }
            AccountCommand.GoToSettingsScreen -> { findNavController().navigate(R.id.action_accountFragment_to_settingsFragment) }
            AccountCommand.GoBackToLoginScreen -> { findNavController().navigate(R.id.action_accountFragment_to_loginFragment) }
        }
    }

}