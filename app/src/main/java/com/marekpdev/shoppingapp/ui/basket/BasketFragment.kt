package com.marekpdev.shoppingapp.ui.basket

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentBasketBinding
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.basket.adapters.*
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class BasketFragment : BaseFragment<BasketState, BasketAction, BasketCommand, FragmentBasketBinding>(R.layout.fragment_basket){

    override val viewModel by viewModels<BasketViewModel>()

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(
                BasketProductAdapterDelegate(
                    onBasketProductClicked = { viewModel.dispatch(BasketAction.BasketProductClicked(it)) },
                    onRemoveBasketProductClicked = { viewModel.dispatch(BasketAction.RemoveBasketProduct(it)) },
                    onUpdateSize = { basketProduct, size -> viewModel.dispatch(BasketAction.UpdateSize(basketProduct, size)) },
                    onUpdateColor = { basketProduct, color -> viewModel.dispatch(BasketAction.UpdateColor(basketProduct, color)) }
                )
            )
            .addDelegate(BasketTotalCostAdapterDelegate())
            .addDelegate(
                BasketContinueCheckoutAdapterDelegate(
                    onContinueCheckoutClicked = { viewModel.dispatch(BasketAction.ContinueCheckout) }
                )
            )
    )

    override fun initLayout(binding: FragmentBasketBinding) = with(binding) {
        rvBasketProducts.layoutManager = LinearLayoutManager(context)
//        rvBasketProducts.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rvBasketProducts.adapter = adapter
    }

    override fun render(state: BasketState) {
        Log.d("FEO33", "Render basket" + System.currentTimeMillis())
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
                findNavController().navigate(R.id.action_basketFragment_to_checkoutFragment)
            }
        }
    }
}