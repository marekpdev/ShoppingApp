package com.marekpdev.shoppingapp.ui.addresses

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAddressesBinding

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class AddressesFragment : Fragment() {
    private lateinit var binding: FragmentAddressesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addresses, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AddressesFragment
//            productViewModel = viewModel
//            btnLogin.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
//            }
//
//            btnRegistration.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_registrationFragment)
//            }
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentAddressesBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        tvAddresses.layoutManager = LinearLayoutManager(context)
        tvAddresses.adapter = AddressesRVAdapter() {
            Log.d("FEO33", "Some click $it")
            findNavController().navigate(R.id.action_addressesFragment_to_addressFragment)
        }
    }

}