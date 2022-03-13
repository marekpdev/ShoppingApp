package com.marekpdev.shoppingapp.ui.orders

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentOrdersBinding
import com.marekpdev.shoppingapp.models.order.Order
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class OrdersFragment : Fragment() {
    private lateinit var binding: FragmentOrdersBinding

    private val onOrderClicked: (Order) -> Unit = {
        Log.d("FEO33", "Clicked order")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(OrdersHeaderAdapterDelegate())
            .addDelegate(OrderAdapterDelegate(onOrderClicked))
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@OrdersFragment
//            productViewModel = viewModel
//            btnLogin.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_loginFragment)
//            }
//
//            btnRegistration.setOnClickListener {
//                findNavController().navigate(R.id.action_accountFragment_to_registrationFragment)
//            }
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentOrdersBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        rvOrders.layoutManager = LinearLayoutManager(context)
        rvOrders.adapter = adapter
        adapter.replaceData(items)
    }

    private val items = mutableListOf<Any>().apply {
        val repo = ProductRepositoryImpl()
        val thisWeekCount = 3

        // regarding headers and items
        // https://github.com/google/iosched/blob/main/mobile/src/main/java/com/google/samples/apps/iosched/ui/agenda/AgendaHeaderIndexer.kt
        // probably here is a good example on how to structure headers + list items
        add("This week $thisWeekCount")
        (1..thisWeekCount).forEach {
            val order = Data.getOrder(it.toLong())
            add(order)
        }

        val lastWeekCount = 5

        add("Last week $lastWeekCount")
        (1..lastWeekCount).forEach {
            val order = Data.getOrder(it.toLong())
            add(order)
        }

        val lastMonthCount = 5

        add("Last month $lastMonthCount")
        (1..lastMonthCount).forEach {
            val order = Data.getOrder(it.toLong())
            add(order)
        }

        Log.d("FEO33", "Added some items")
    }

}