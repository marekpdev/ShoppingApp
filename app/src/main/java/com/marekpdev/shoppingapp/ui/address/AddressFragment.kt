package com.marekpdev.shoppingapp.ui.address

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAddressBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.product.ProductFragmentArgs
import com.marekpdev.shoppingapp.ui.product.ProductViewModel
import com.marekpdev.shoppingapp.utils.setTextIfDifferent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class AddressFragment : BaseFragment<AddressState, AddressAction, AddressCommand, FragmentAddressBinding>(R.layout.fragment_address) {

    private val navArgs: AddressFragmentArgs by navArgs()

    @Inject
    lateinit var addressViewModelFactory: AddressViewModel.Factory

    override val viewModel by viewModels<AddressViewModel> {
        AddressViewModel.provideFactory(
            assistedFactory = addressViewModelFactory,
            addressId = navArgs.addressId
        )
    }

    override fun initLayout(binding: FragmentAddressBinding) = with(binding){
        btnAdd.setOnClickListener { viewModel.dispatch(AddressAction.AddAddress) }
        btnUpdate.setOnClickListener { viewModel.dispatch(AddressAction.UpdateAddress) }
    }

    override fun render(state: AddressState) {
        binding.apply {
            tvHeader.text = when(state.mode){
                Mode.ADD -> "Add Address"
                Mode.UPDATE -> "Update Address"
            }

            tvAddressLine1.setTextIfDifferent(state.line1)
            tvAddressLine2.setTextIfDifferent(state.line2)
            tvPostcode.setTextIfDifferent(state.postcode)
            tvCity.setTextIfDifferent(state.city)
            tvCountry.setTextIfDifferent(state.country)

            btnAdd.visibility = if(state.mode == Mode.ADD) View.VISIBLE else View.GONE
            btnUpdate.visibility = if(state.mode == Mode.UPDATE) View.VISIBLE else View.GONE
        }
    }

    override fun onCommand(command: AddressCommand) {
        when(command){
            is AddressCommand.GoBackToAddressesScreen -> {
                // todo
            }
        }
    }

}