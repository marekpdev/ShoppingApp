package com.marekpdev.shoppingapp.ui.paymentmethods

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentPaymentMethodsBinding
import com.marekpdev.shoppingapp.models.payments.PaymentCard
import com.marekpdev.shoppingapp.models.payments.PaymentMethod
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.AddressesCommand
import com.marekpdev.shoppingapp.ui.addresses.AddressesFragmentDirections
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.paymentmethods.adapters.AddPaymentCard
import com.marekpdev.shoppingapp.ui.paymentmethods.adapters.AddPaymentCardAdapterDelegate
import com.marekpdev.shoppingapp.ui.paymentmethods.adapters.PaymentCardAdapterDelegate
import com.marekpdev.shoppingapp.ui.paymentmethods.adapters.PaymentMethodAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class PaymentMethodsFragment : BaseFragment<PaymentMethodsState, PaymentMethodsAction, PaymentMethodsCommand, FragmentPaymentMethodsBinding>(R.layout.fragment_payment_methods) {

    override val viewModel by viewModels<PaymentMethodsViewModel>()

    private val onPaymentMethodClicked: (PaymentMethod) -> Unit = {
        viewModel.dispatch(PaymentMethodsAction.PaymentMethodClicked(it))
    }

    private val onPaymentCardClicked: (PaymentCard) -> Unit = {
        viewModel.dispatch(PaymentMethodsAction.PaymentCardClicked(it))
    }

    private val onAddPaymentCardClicked: () -> Unit = {
        viewModel.dispatch(PaymentMethodsAction.AddPaymentCardClicked)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
//            .addDelegate(PaymentMethodAdapterDelegate(onPaymentMethodClicked))
            .addDelegate(PaymentCardAdapterDelegate(onPaymentCardClicked))
            .addDelegate(AddPaymentCardAdapterDelegate(onAddPaymentCardClicked))
    )

    override fun initLayout(binding: FragmentPaymentMethodsBinding) = with(binding){
        rvPaymentMethods.layoutManager = LinearLayoutManager(context)
        rvPaymentMethods.adapter = adapter
    }

    override fun render(state: PaymentMethodsState) {
        binding.apply {
            pbPaymentMethods.visibility = if(state.loading) View.VISIBLE else View.GONE
            adapter.replaceData(listOf(AddPaymentCard) + state.paymentMethods)
        }
    }

    override fun onCommand(command: PaymentMethodsCommand) {
        when(command){
            is PaymentMethodsCommand.GoToPaymentCardDetails -> {
                findNavController().navigate(PaymentMethodsFragmentDirections.actionPaymentMethodsFragmentToPaymentCardFragment(paymentCardId = command.paymentCard.id))
            }
            is PaymentMethodsCommand.GoToAddPaymentCardScreen -> {
                findNavController().navigate(PaymentMethodsFragmentDirections.actionPaymentMethodsFragmentToPaymentCardFragment())
            }
        }
    }

}