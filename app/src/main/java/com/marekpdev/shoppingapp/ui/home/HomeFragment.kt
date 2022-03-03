package com.marekpdev.shoppingapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentHomeBinding
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.repository.products.ProductRepositoryImpl
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.banner.HomeBannerImages
import com.marekpdev.shoppingapp.ui.home.products.HomeProducts
import com.marekpdev.shoppingapp.ui.home.products.HomeProductsAdapterDelegate
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeader
import com.marekpdev.shoppingapp.ui.home.productsheader.HomeProductsHeaderAdapterDelegate

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .addDelegate(HomeBannerAdapterDelegate {})
            .addDelegate(HomeProductsHeaderAdapterDelegate {})
            .addDelegate(HomeProductsAdapterDelegate {})
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentHomeBinding) = binding.apply {
        rvHomeContent.layoutManager = LinearLayoutManager(context)
        rvHomeContent.adapter = adapter
        adapter.replaceData(items)
    }

    private val items = mutableListOf<Any>().apply {
        add(HomeBannerImages(listOf(
            R.drawable.home_banner_1,
            R.drawable.home_banner_2,
            R.drawable.home_banner_3))
        )

        add(HomeProductsHeader("Best sellers"))
        add(HomeProducts(Data.getMenu().second.shuffled().take(8)))
        add(HomeProductsHeader("Just arrived"))
        add(HomeProducts(Data.getMenu().second.shuffled().take(3)))
        add(HomeProductsHeader("Discover more"))
        add(HomeProducts(Data.getMenu().second.shuffled().take(5)))
    }

}