package com.marekpdev.shoppingapp.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentEditProfileBinding
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
import dagger.hilt.android.AndroidEntryPoint
import org.joda.time.DateTime

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class OrdersFragment : BaseFragment<OrdersState, OrdersAction, OrdersCommand, FragmentOrdersBinding>(R.layout.fragment_orders) {

    override val viewModel by viewModels<OrdersViewModel>()

    private val onOrderClicked: (Order) -> Unit = {
        Log.d("FEO33", "On order clicked")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(OrdersHeaderAdapterDelegate())
            .addDelegate(OrderAdapterDelegate(onOrderClicked))
        )


    override fun initLayout(binding: FragmentOrdersBinding) = with(binding){
        rvOrders.layoutManager = LinearLayoutManager(context)
        rvOrders.adapter = adapter
    }

    override fun render(state: OrdersState) {
        binding.apply {
            pbOrders.visibility = if(state.loading) View.VISIBLE else View.GONE

            val now = DateTime.now()
            val items = mutableListOf<Any>()

            state.orders.groupBy {
                OrderGroup.getOrderGroup(DateTime(it.createdAt), now)
            }.forEach { (orderGroup, orders) ->
                items.add(orderGroup.label)
                orders.forEach { order -> items.add(order) }
            }
            adapter.replaceData(items)
        }
    }

    override fun onCommand(command: OrdersCommand) {
        when (command) {
            OrdersCommand.GoBackToAccountScreen -> {
                // todo
            }
        }
    }

    private val items = mutableListOf<Any>().apply {
//        val repo = ProductsRepositoryImpl()
//        val thisWeekCount = 3
//
//        // regarding headers and items
//        // https://github.com/google/iosched/blob/main/mobile/src/main/java/com/google/samples/apps/iosched/ui/agenda/AgendaHeaderIndexer.kt
//        // probably here is a good example on how to structure headers + list items
//        add("This week $thisWeekCount")
//        (1..thisWeekCount).forEach {
//            val order = Data.getOrder(it.toLong())
//            add(order)
//        }
//
//        val lastWeekCount = 5
//
//        add("Last week $lastWeekCount")
//        (1..lastWeekCount).forEach {
//            val order = Data.getOrder(it.toLong())
//            add(order)
//        }
//
//        val lastMonthCount = 5
//
//        add("Last month $lastMonthCount")
//        (1..lastMonthCount).forEach {
//            val order = Data.getOrder(it.toLong())
//            add(order)
//        }
    }

}