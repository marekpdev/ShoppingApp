package com.marekpdev.shoppingapp.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentFavouriteBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.productvh.ProductWidthConstAdapterDelegate
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class FavouriteFragment : Fragment(), MviView<FavouriteState, FavouriteCommand> {

    private val viewModel by viewModels<FavouriteViewModel>()

    private lateinit var binding: FragmentFavouriteBinding

    private var productUnfavouredSnackbar: Snackbar? = null

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FavouriteFragment
            initLayout(this)
        }
    }


    private fun initLayout(binding: FragmentFavouriteBinding) = binding.apply {
        rvFavourites.layoutManager = GridLayoutManager(requireContext(), 2)
        rvFavourites.adapter = adapter

        viewModel.bind(viewLifecycleOwner, this@FavouriteFragment)
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