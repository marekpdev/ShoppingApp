package com.marekpdev.shoppingapp.ui.order

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentOrderBinding
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class OrderFragment : BaseFragment<OrderState, OrderAction, OrderCommand, FragmentOrderBinding>(R.layout.fragment_order) {

    private val navArgs: OrderFragmentArgs by navArgs()

    @Inject
    lateinit var orderViewModelFactory: OrderViewModel.Factory

    override val viewModel by viewModels<OrderViewModel> {
        OrderViewModel.provideFactory(
            assistedFactory = orderViewModelFactory,
            orderId = navArgs.orderId
        )
    }

    override fun initLayout(binding: FragmentOrderBinding) = with(binding){

    }

    override fun render(state: OrderState) {
        binding.apply {
            pbOrder.visibility = if(state.loading) View.VISIBLE else View.GONE

            if(state.order != null){
                tvContent.text = "Order #${state.order.id} loaded"
            } else {
                tvContent.text = "N/A"
            }
        }
    }

    override fun onCommand(command: OrderCommand) {
        when (command) {
            OrderCommand.TestOrderCommand -> {
                // todo
            }
        }
    }

}