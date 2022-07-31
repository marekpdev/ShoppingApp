package com.marekpdev.shoppingapp.ui.ordersummary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentOrderSummaryDetailsBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.checkout.adapters.CheckoutDeliveryAddressDelegate
import com.marekpdev.shoppingapp.ui.checkout.adapters.CheckoutPaymentMethodDelegate


/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class OrderSummaryFragment : Fragment() {

    private lateinit var binding: FragmentOrderSummaryDetailsBinding

    private val onProductClicked: (Product) -> Unit = {

    }

    private val onProductLongClicked: (Product) -> Unit = {

    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(CheckoutDeliveryAddressDelegate {})
            .addDelegate(CheckoutPaymentMethodDelegate {})
//            .addDelegate(BasketProductAdapterDelegate(onProductClicked, onProductLongClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_order_summary_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@OrderSummaryFragment
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

    private fun initLayout(binding: FragmentOrderSummaryDetailsBinding) = binding.apply {
        val items = mutableListOf<Any>()
//        items.add(Data.getAddress())
//        items.add(PaymentMethod("VISA - **** **** **** 4342"))
//        items.addAll(Basket.basketItems)

        rvOrderDetails.layoutManager = LinearLayoutManager(context)
        rvOrderDetails.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvOrderDetails.adapter = adapter
        adapter.replaceData(items)
    }
}