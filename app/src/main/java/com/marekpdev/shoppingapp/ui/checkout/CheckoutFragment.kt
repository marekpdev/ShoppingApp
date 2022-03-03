package com.marekpdev.shoppingapp.ui.checkout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentCheckoutBinding
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Basket
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.AddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.search.SearchFragmentDirections

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class CheckoutFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked Product")
    }

    private val onProductLongClicked: (Product) -> Unit = {
        Log.d("FEO33", "Long clicked Product")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(BasketProductAdapterDelegate(onProductClicked, onProductLongClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@CheckoutFragment
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

    private fun initLayout(binding: FragmentCheckoutBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        val items = Basket.basketItems
        Log.d("FEO33", "Basket items: ${items.size}")

        rvBasketProducts.layoutManager = LinearLayoutManager(context)
        rvBasketProducts.adapter = adapter
        adapter.replaceData(items)
    }
}