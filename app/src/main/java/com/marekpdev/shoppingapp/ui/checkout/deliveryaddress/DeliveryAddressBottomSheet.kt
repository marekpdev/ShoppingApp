package com.marekpdev.shoppingapp.ui.checkout.deliveryaddress

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetCheckoutDeliveryAddressBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.base.BaseBottomSheetDialogFragment
import com.marekpdev.shoppingapp.ui.checkout.CheckoutAction
import com.marekpdev.shoppingapp.ui.checkout.CheckoutCommand
import com.marekpdev.shoppingapp.ui.checkout.CheckoutState
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
@AndroidEntryPoint
class DeliveryAddressBottomSheet: BaseBottomSheetDialogFragment<CheckoutState, CheckoutAction, CheckoutCommand, BottomSheetCheckoutDeliveryAddressBinding>(R.layout.bottom_sheet_checkout_delivery_address) {

    companion object {
        const val TAG = "ModalBottomSheetDeliveryAddress"
    }

    override val viewModel by viewModels<DeliveryAddressBottomSheetViewModel>()

    private val onAddressClicked: (Address) -> Unit = {
        viewModel.dispatch(CheckoutAction.SelectDeliveryAddress(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(AddressAdapterDelegate(onAddressClicked))
    )

    override fun initLayout(binding: BottomSheetCheckoutDeliveryAddressBinding) = with(binding) {
        BottomSheetBehavior.from(binding.llBottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

        rvAddresses.layoutManager = LinearLayoutManager(context)
        rvAddresses.adapter = adapter
    }

    override fun render(state: CheckoutState) {
        binding.apply {
            adapter.replaceData(state.deliveryAddresses)
        }
    }

    override fun onCommand(command: CheckoutCommand) {
        when(command){
            is CheckoutCommand.HideDeliveryAddressBottomSheet -> dismiss()
        }
    }

}