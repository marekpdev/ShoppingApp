package com.marekpdev.shoppingapp.ui.checkout.paymentmethod

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetCheckoutPaymentMethodBinding
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseBottomSheetDialogFragment
import com.marekpdev.shoppingapp.ui.checkout.CheckoutAction
import com.marekpdev.shoppingapp.ui.checkout.CheckoutCommand
import com.marekpdev.shoppingapp.ui.checkout.CheckoutState
import com.marekpdev.shoppingapp.ui.paymentmethods.adapters.PaymentCardAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
@AndroidEntryPoint
class PaymentMethodBottomSheet: BaseBottomSheetDialogFragment<CheckoutState, CheckoutAction, CheckoutCommand, BottomSheetCheckoutPaymentMethodBinding>(R.layout.bottom_sheet_checkout_payment_method) {

    companion object {
        const val TAG = "ModalBottomSheetPaymentMethod"
    }

    override val viewModel by viewModels<PaymentMethodBottomSheetViewModel>()

    private val onPaymentCardClicked: (PaymentCard) -> Unit = {
        viewModel.dispatch(CheckoutAction.SelectPaymentCard(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(PaymentCardAdapterDelegate(onPaymentCardClicked))
    )

    override fun initLayout(binding: BottomSheetCheckoutPaymentMethodBinding) = with(binding) {
        BottomSheetBehavior.from(binding.llBottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

        rvPaymentMethods.layoutManager = LinearLayoutManager(context)
        rvPaymentMethods.adapter = adapter
    }

    override fun render(state: CheckoutState) {
        binding.apply {
            adapter.replaceData(state.paymentMethods)
        }
    }

    override fun onCommand(command: CheckoutCommand) {
        when(command){
            is CheckoutCommand.HidePaymentMethodBottomSheet -> dismiss()
        }
    }

}