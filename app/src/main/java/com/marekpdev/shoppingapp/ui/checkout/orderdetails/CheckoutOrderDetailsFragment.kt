package com.marekpdev.shoppingapp.ui.checkout.orderdetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentCheckoutOrderDetailsBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.order.PaymentMethod
import com.marekpdev.shoppingapp.repository.Basket
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.checkout.CheckoutDeliveryAddressDelegate
import com.marekpdev.shoppingapp.ui.checkout.CheckoutPaymentMethodDelegate
import com.marekpdev.shoppingapp.ui.checkout.CheckoutProductAdapterDelegate


/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class CheckoutOrderDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutOrderDetailsBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked Product")
    }

    private val onProductLongClicked: (Product) -> Unit = {
        Log.d("FEO33", "Long clicked Product")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(CheckoutDeliveryAddressDelegate {})
            .addDelegate(CheckoutPaymentMethodDelegate {})
            .addDelegate(CheckoutProductAdapterDelegate(onProductClicked, onProductLongClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_order_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@CheckoutOrderDetailsFragment
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

    private fun initLayout(binding: FragmentCheckoutOrderDetailsBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        val items = mutableListOf<Any>()
//        items.add(Data.getAddress())
        items.add(PaymentMethod("VISA - **** **** **** 4342"))
//        items.addAll(Basket.basketItems)

        rvOrderDetails.layoutManager = LinearLayoutManager(context)
        rvOrderDetails.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvOrderDetails.adapter = adapter
        adapter.replaceData(items)
    }
}