package com.marekpdev.shoppingapp.ui.favourite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentFavouriteBinding
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.search.ProductAdapterDelegate

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

    private val onProductClicked: (Product) -> Unit = {
        Log.d("FEO33", "Clicked product")
    }

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(ProductWidthConstAdapterDelegate(onProductClicked))
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
        adapter.replaceData(items)
    }

    private val items = Data.getMenu().second
}