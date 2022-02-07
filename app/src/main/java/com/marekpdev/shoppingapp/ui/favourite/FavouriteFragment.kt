package com.marekpdev.shoppingapp.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentFavouriteBinding
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class FavouriteFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding

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
        rvFavourites.adapter = ProductGridRVAdapter(
            ProductRepositoryImpl().getProducts(10)
        )
        rvFavourites.layoutManager = GridLayoutManager(requireContext(), 2)
    }
}