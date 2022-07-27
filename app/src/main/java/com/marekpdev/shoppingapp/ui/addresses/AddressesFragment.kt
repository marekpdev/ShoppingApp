package com.marekpdev.shoppingapp.ui.addresses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAddressesBinding
import com.marekpdev.shoppingapp.databinding.FragmentOrdersBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import com.marekpdev.shoppingapp.ui.orders.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class AddressesFragment : BaseFragment<AddressesState, AddressesAction, AddressesCommand, FragmentAddressesBinding>(R.layout.fragment_addresses) {

    override val viewModel by viewModels<AddressesViewModel>()

    private val onAddressClicked: (Address) -> Unit = {
        viewModel.dispatch(AddressesAction.AddressClicked(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(AddressAdapterDelegate(onAddressClicked))
    )

    override fun initLayout(binding: FragmentAddressesBinding) = with(binding){
        rvAddresses.layoutManager = LinearLayoutManager(context)
        rvAddresses.adapter = adapter
    }

    override fun render(state: AddressesState) {
        binding.apply {
            pbAddresses.visibility = if(state.loading) View.VISIBLE else View.GONE
            adapter.replaceData(state.addresses)
        }
    }

    override fun onCommand(command: AddressesCommand) {
        when (command) {
            is AddressesCommand.GoToAddressDetails -> {
                findNavController().navigate(AddressesFragmentDirections.actionAddressesFragmentToAddressFragment(addressId = command.address.id))
            }
            AddressesCommand.GoBackToAccountScreen -> {
                // todo

            }
        }
    }


}