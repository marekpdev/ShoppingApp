package com.marekpdev.shoppingapp.ui.ordercomplete

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentOrderBinding
import com.marekpdev.shoppingapp.databinding.FragmentOrderCompleteBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.order.OrderAction
import com.marekpdev.shoppingapp.ui.order.OrderState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class OrderCompleteFragment : BaseFragment<OrderCompleteState, OrderCompleteAction, OrderCompleteCommand, FragmentOrderCompleteBinding>(R.layout.fragment_order_complete) {

    private val navArgs: OrderCompleteFragmentArgs by navArgs()

    @Inject
    lateinit var orderCompleteViewModelFactory: OrderCompleteViewModel.Factory

    override val viewModel by viewModels<OrderCompleteViewModel> {
        OrderCompleteViewModel.provideFactory(
            assistedFactory = orderCompleteViewModelFactory,
            orderId = navArgs.orderId
        )
    }

    override fun initLayout(binding: FragmentOrderCompleteBinding) = with(binding){

    }

    override fun render(state: OrderCompleteState) {
        binding.apply {
            pbOrder.visibility = if(state.loading) View.VISIBLE else View.GONE

            if(state.order != null){
                tvContent.text = "Order #${state.order.id} complete"
            } else {
                tvContent.text = "N/A"
            }
        }
    }

    override fun onCommand(command: OrderCompleteCommand) {
        when (command) {
            is OrderCompleteCommand.GoBackToBasket -> {
                // todo
            }
            is OrderCompleteCommand.GoToOrderDetails -> {
                // todo
            }
        }
    }

}