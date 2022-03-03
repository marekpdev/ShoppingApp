package com.marekpdev.shoppingapp.ui.checkout.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentBasketBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Basket
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked Product")
    }

    private val onProductLongClicked: (Product) -> Unit = {
        Log.d("FEO33", "Long clicked Product")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(CheckoutProductAdapterDelegate(onProductClicked, onProductLongClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_basket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@BasketFragment
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

    private fun initLayout(binding: FragmentBasketBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        val items = Basket.basketItems
        Log.d("FEO33", "Basket items: ${items.size}")

        rvBasketProducts.layoutManager = LinearLayoutManager(context)
        rvBasketProducts.adapter = adapter
        adapter.replaceData(items)
    }
}