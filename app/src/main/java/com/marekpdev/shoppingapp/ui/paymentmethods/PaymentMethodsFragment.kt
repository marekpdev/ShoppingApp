package com.marekpdev.shoppingapp.ui.paymentmethods

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
import com.marekpdev.shoppingapp.databinding.FragmentPaymentMethodsBinding
import com.marekpdev.shoppingapp.models.PaymentCard
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class PaymentMethodsFragment : Fragment() {
    private lateinit var binding: FragmentPaymentMethodsBinding

    private val onPaymentCardClicked: (PaymentCard) -> Unit = {
        findNavController().navigate(R.id.action_paymentMethodsFragment_to_paymentCardFragment)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(PaymentCardAdapterDelegate(onPaymentCardClicked))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_methods, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@PaymentMethodsFragment
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

    private fun initLayout(binding: FragmentPaymentMethodsBinding) = binding.apply {
        rvPaymentMethods.layoutManager = LinearLayoutManager(context)
        rvPaymentMethods.adapter = adapter
        adapter.replaceData(items)
    }

    private val items = (1..4).map { id ->
        PaymentCard(id, "hash$id", "VISA", "123${id}")
    }


}