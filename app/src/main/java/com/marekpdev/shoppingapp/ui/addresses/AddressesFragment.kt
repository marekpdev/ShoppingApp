package com.marekpdev.shoppingapp.ui.addresses

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
import com.marekpdev.shoppingapp.databinding.FragmentAddressesBinding
import com.marekpdev.shoppingapp.models.Address
import com.marekpdev.shoppingapp.models.Order
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.orders.OrderAdapterDelegate
import com.marekpdev.shoppingapp.ui.orders.OrdersHeaderAdapterDelegate
import com.marekpdev.shoppingapp.utils.RVDisplayableItem

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class AddressesFragment : Fragment() {
    private lateinit var binding: FragmentAddressesBinding

    private val onAddressClicked: (Address) -> Unit = {
        Log.d("FEO33", "Clicked order")
        findNavController().navigate(R.id.action_addressesFragment_to_addressFragment)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(AddressAdapterDelegate(onAddressClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_addresses, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@AddressesFragment
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

    private fun initLayout(binding: FragmentAddressesBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        rvAddresses.layoutManager = LinearLayoutManager(context)
        rvAddresses.adapter = adapter
        adapter.replaceData(items)
    }

    private val items = mutableListOf<Any>().apply {
        (1..5).forEach {
            add(Address("line1 - $it", "line2", "postcode", "city", "country"))
        }
    }

}