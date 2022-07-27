package com.marekpdev.shoppingapp.ui.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentEditProfileBinding
import com.marekpdev.shoppingapp.databinding.FragmentOrderBinding
import com.marekpdev.shoppingapp.databinding.FragmentOrdersBinding
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.base.BaseViewModel
import com.marekpdev.shoppingapp.ui.editprofile.EditProfileAction
import com.marekpdev.shoppingapp.ui.editprofile.EditProfileCommand
import com.marekpdev.shoppingapp.ui.editprofile.EditProfileState
import com.marekpdev.shoppingapp.ui.editprofile.EditProfileViewModel
import com.marekpdev.shoppingapp.ui.orders.adapters.OrderAdapterDelegate
import com.marekpdev.shoppingapp.ui.orders.adapters.OrdersHeaderAdapterDelegate
import com.marekpdev.shoppingapp.ui.product.ProductFragmentArgs
import com.marekpdev.shoppingapp.ui.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime
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