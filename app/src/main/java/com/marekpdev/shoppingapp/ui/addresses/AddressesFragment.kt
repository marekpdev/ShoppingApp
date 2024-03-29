package com.marekpdev.shoppingapp.ui.addresses

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAddressesBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddAddress
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddAddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.base.BaseFragment
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

    private val onAddAddressClicked: () -> Unit = {
        viewModel.dispatch(AddressesAction.AddAddressClicked)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(AddressAdapterDelegate(onAddressClicked))
            .addDelegate(AddAddressAdapterDelegate(onAddAddressClicked))
    )

    override fun initLayout(binding: FragmentAddressesBinding) = with(binding){
        rvAddresses.layoutManager = LinearLayoutManager(context)
        rvAddresses.adapter = adapter
    }

    override fun render(state: AddressesState) {
        binding.apply {
            pbAddresses.visibility = if(state.loading) View.VISIBLE else View.GONE
            adapter.replaceData(listOf(AddAddress) + state.addresses)
        }
    }

    override fun onCommand(command: AddressesCommand) {
        when (command) {
            is AddressesCommand.GoToAddressDetails -> {
                findNavController().navigate(AddressesFragmentDirections.actionAddressesFragmentToAddressFragment(addressId = command.address.id))
            }
            is AddressesCommand.GoToAddAddressScreen -> {
                findNavController().navigate(AddressesFragmentDirections.actionAddressesFragmentToAddressFragment())
            }
            AddressesCommand.GoBackToAccountScreen -> {
                // todo

            }
        }
    }


}