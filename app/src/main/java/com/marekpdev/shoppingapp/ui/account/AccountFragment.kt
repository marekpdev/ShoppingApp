package com.marekpdev.shoppingapp.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAccountBinding
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AccountFragment
//            productViewModel = viewModel
            btnEditProfile.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_editProfileFragment)
            }
            btnOrders.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_ordersFragment)
            }
            btnAddresses.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_addressesFragment)
            }
            btnPaymentMethods.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_paymentMethodsFragment)
            }
            btnSettings.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_settingsFragment)
            }
            btnLogout.setOnClickListener {
                findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
            }
        }
    }

}