package com.marekpdev.shoppingapp.ui.favourite

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentFavouriteBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.productvh.ProductWidthConstAdapterDelegate
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class FavouriteFragment : BaseFragment<FavouriteState, FavouriteAction, FavouriteCommand, FragmentFavouriteBinding>(R.layout.fragment_favourite) {

    override val viewModel by viewModels<FavouriteViewModel>()

    private val onProductClicked: (Product) -> Unit = {
        viewModel.dispatch(FavouriteAction.ProductClicked(it.id))
    }

    private val onToggleFavourite: (Product) -> Unit = {
        viewModel.dispatch(FavouriteAction.ToggleFavouriteClicked(it))
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(ProductWidthConstAdapterDelegate(onProductClicked, onToggleFavourite))
    )

    private var productUnfavouredSnackbar: Snackbar? = null

    override fun initLayout(binding: FragmentFavouriteBinding) = with(binding) {
        rvFavourites.layoutManager = GridLayoutManager(requireContext(), 2)
        rvFavourites.adapter = adapter
    }

    override fun render(state: FavouriteState) {
        binding.apply {
            adapter.replaceData(state.products)
            pbFavourites.visibility = when (state.loading) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    override fun onCommand(command: FavouriteCommand) {
        when(command){
            is FavouriteCommand.ShowProductUnfavoured -> {
                productUnfavouredSnackbar?.dismiss()
                Snackbar.make(binding.root, "${command.product.name} removed from favourites", Snackbar.LENGTH_LONG)
                    .apply { setAction("UNDO") { onToggleFavourite(command.product) } }
                    .also { productUnfavouredSnackbar = it }
                    .show()
            }
        }
    }
}