package com.marekpdev.shoppingapp.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class ProductFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product, container, false)

        val viewPager = view.findViewById<ViewPager2>(R.id.vpProductImages)
        viewPager.adapter = ImagesAdapter(
            listOf(
                R.drawable.product1,
                R.drawable.product2,
                R.drawable.product3,
                R.drawable.product4,
            )
        )

        val tlProductImages = view.findViewById<TabLayout>(R.id.tlProductImages)

        TabLayoutMediator(tlProductImages, viewPager) { tab, position ->}.attach()

//        view.findViewById<Button>(R.id.signup_btn).setOnClickListener {
//            findNavController().navigate(R.id.action_register_to_registered)
//        }
        return view
    }

}