package com.marekpdev.shoppingapp.ui.basket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentBasketBinding
import com.marekpdev.shoppingapp.models.BasketProduct
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.basket.adapters.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class BasketFragment : Fragment(), MviView<BasketState, BasketCommand> {

    private val viewModel by viewModels<BasketViewModel>()

    private lateinit var binding: FragmentBasketBinding

    private val onBasketProductClicked: (BasketProduct) -> Unit = {
        viewModel.dispatch(BasketAction.BasketProductClicked(it.id))
    }

    private val onContinueCheckout: () -> Unit = {
        Log.d("FEO33", "onContinueCheckout 1")
        viewModel.dispatch(BasketAction.ContinueCheckout)
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(BasketProductAdapterDelegate(onBasketProductClicked))
            .addDelegate(BasketTotalCostAdapterDelegate())
            .addDelegate(BasketContinueCheckoutAdapterDelegate(onContinueCheckout))
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
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentBasketBinding) = binding.apply {
        rvBasketProducts.layoutManager = LinearLayoutManager(context)
//        rvBasketProducts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvBasketProducts.adapter = adapter

        viewModel.bind(viewLifecycleOwner, this@BasketFragment)
    }

    override fun render(state: BasketState) {
        binding.apply {
            val items = mutableListOf<Any>().apply {
                state.basketProducts.forEach { add(it) }
                add(TotalCost(state.totalCost))
                add(ContinueCheckout)
            }

            adapter.replaceData(items)
        }
    }

    override fun onCommand(command: BasketCommand) {
        when (command){
            is BasketCommand.ContinueCheckout -> {
                Log.d("FEO33", "onContinueCheckout 2")
                findNavController().navigate(R.id.action_checkoutFragment_to_checkoutOrderDetailsFragment)
            }
        }
    }
}