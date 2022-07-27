package com.marekpdev.shoppingapp.ui.paymentmethods

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentPaymentMethodsBinding
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
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

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(PaymentMethodAdapterDelegate(onPaymentMethodClicked))
    )

    override fun initLayout(binding: FragmentPaymentMethodsBinding) = with(binding){
        rvPaymentMethods.layoutManager = LinearLayoutManager(context)
        rvPaymentMethods.adapter = adapter
    }

    override fun render(state: PaymentMethodsState) {
        binding.apply {
            pbPaymentMethods.visibility = if(state.loading) View.VISIBLE else View.GONE
            adapter.replaceData(state.paymentMethods)
        }
    }

    override fun onCommand(command: PaymentMethodsCommand) {
        when(command){
            is PaymentMethodsCommand.GoToPaymentMethodDetails -> {
                // todo
                //findNavController().navigate(R.id.action_paymentMethodsFragment_to_paymentCardFragment)
            }
        }
    }

}