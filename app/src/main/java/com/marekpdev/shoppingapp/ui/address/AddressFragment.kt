package com.marekpdev.shoppingapp.ui.address

import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAddressBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
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

        val onContentChanged = {
            viewModel.dispatch(AddressAction.OnContentChanged(
                line1 = tvAddressLine1.text.toString(),
                line2 = tvAddressLine2.text.toString(),
                postcode = tvPostcode.text.toString(),
                city = tvCity.text.toString(),
                country = tvCountry.text.toString(),
            ))

        }

        listOf(tvAddressLine1, tvAddressLine2, tvPostcode, tvCity, tvCountry).forEach {
            it.doAfterTextChanged { onContentChanged() }
        }
    }

    override fun render(state: AddressState) {
        binding.apply {
            tvHeader.text = when(state.mode){
                Mode.ADD -> "Add Address"
                Mode.UPDATE -> "Update Address"
            }

            pbLoading.visibility = if(state.loading) View.VISIBLE else View.GONE

            tvAddressLine1.setTextIfDifferent(state.line1)
            tvAddressLine2.setTextIfDifferent(state.line2)
            tvPostcode.setTextIfDifferent(state.postcode)
            tvCity.setTextIfDifferent(state.city)
            tvCountry.setTextIfDifferent(state.country)

            tvAddressLine1.isEnabled = !state.loading
            tvAddressLine2.isEnabled = !state.loading
            tvPostcode.isEnabled = !state.loading
            tvCity.isEnabled = !state.loading
            tvCountry.isEnabled = !state.loading

            btnAdd.visibility = if(state.mode == Mode.ADD) View.VISIBLE else View.GONE
            btnUpdate.visibility = if(state.mode == Mode.UPDATE) View.VISIBLE else View.GONE
        }
    }

    override fun onCommand(command: AddressCommand) {
        when(command){
            is AddressCommand.GoBackToAddressesScreen -> {
                findNavController().navigate(R.id.action_addressFragment_to_addressesFragment)
            }
        }
    }

}