package com.marekpdev.shoppingapp.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentCheckoutBinding
import com.marekpdev.shoppingapp.databinding.FragmentOrdersBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.checkout.adapters.*
import com.marekpdev.shoppingapp.ui.checkout.deliveryaddress.DeliveryAddressBottomSheet
import com.marekpdev.shoppingapp.ui.checkout.paymentmethod.PaymentMethodBottomSheet
import com.marekpdev.shoppingapp.ui.orders.OrdersAction
import com.marekpdev.shoppingapp.ui.orders.OrdersCommand
import com.marekpdev.shoppingapp.ui.orders.OrdersState
import com.marekpdev.shoppingapp.ui.orders.OrdersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class CheckoutFragment : BaseFragment<CheckoutState, CheckoutAction, CheckoutCommand, FragmentCheckoutBinding>(R.layout.fragment_checkout) {

    override val viewModel by viewModels<CheckoutViewModel>()

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(CheckoutDeliveryAddressDelegate { viewModel.dispatch(CheckoutAction.SelectDeliveryAddressClicked)})
            .addDelegate(CheckoutPaymentMethodDelegate { viewModel.dispatch(CheckoutAction.SelectPaymentMethodClicked)})
            .addDelegate(CheckoutSelectDeliveryAddressDelegate { viewModel.dispatch(CheckoutAction.SelectDeliveryAddressClicked)})
            .addDelegate(CheckoutSelectPaymentMethodDelegate { viewModel.dispatch(CheckoutAction.SelectPaymentMethodClicked)})
            .addDelegate(CheckoutPlaceOrderAdapterDelegate { viewModel.dispatch(CheckoutAction.PlaceOrder)})
    )

    override fun initLayout(binding: FragmentCheckoutBinding) = with(binding){
        rvCheckout.layoutManager = LinearLayoutManager(context)
        rvCheckout.adapter = adapter
    }

    override fun render(state: CheckoutState) {
        binding.apply {
//            pbCheckout.visibility = if(state.loading) View.VISIBLE else View.GONE

            val items = mutableListOf<Any>().apply {
                add(state.selectedDeliveryAddress ?: SelectDeliveryAddress)
                add(state.selectedPaymentMethod ?: SelectPaymentMethod)
                add(PlaceOrder)
            }
            adapter.replaceData(items)
        }
    }

    override fun onCommand(command: CheckoutCommand) {
        when(command){
            is CheckoutCommand.ShowDeliveryAddressBottomSheet -> {
                DeliveryAddressBottomSheet().show(parentFragmentManager, DeliveryAddressBottomSheet.TAG)
            }
            is CheckoutCommand.ShowPaymentMethodBottomSheet -> {
                PaymentMethodBottomSheet().show(parentFragmentManager, PaymentMethodBottomSheet.TAG)
            }
            is CheckoutCommand.GoToOrderCompleteScreen -> {
                findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToOrderCompleteFragment(orderId = command.orderId))
            }
        }
    }
}