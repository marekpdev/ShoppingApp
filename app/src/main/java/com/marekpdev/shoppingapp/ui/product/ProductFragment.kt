package com.marekpdev.shoppingapp.ui.product

import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class ProductFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
//        val tvLong = view.findViewById<TextView>(R.id.tvLongText)
//        (0..20).forEach {
//            tvLong.text = tvLong.text.toString() + "ejgregre\nregreger\n\njgjgnjegrer"
//        }
//        tvLong.text = tvLong.text.toString() + "ENDDD----------"


        // there was an issue with clipping when padding == 0
        // (words were going beyond the shape) - for the moment it has been fixed
        // by just applying padding == 16 but we might look at it later on if needed
        val scrollViewProductCard = view.findViewById<NestedScrollView>(R.id.scrollViewProductCard)
//        scrollViewProductCard.outlineProvider = ViewOutlineProvider.PADDED_BOUNDS
//        scrollViewProductCard.clipToOutline = true


        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.share -> {
                    Log.d("FEO33", "Clicked share")
                    // Handle favorite icon press
                    true
                }
                R.id.favorite -> {
                    Log.d("FEO33", "Clicked fav")
                    // Handle favorite icon press
                    true
                }
                else -> false
            }
        }







        // SIZES







        val chipGroupSizes = view.findViewById<ChipGroup>(R.id.chipGroupSizes)

        val sizes = (1..9).map { Size(it, "0$it") }
        sizes.forEach { size ->
            val chip = LayoutInflater.from(context).inflate(R.layout.product_size_chip, null) as Chip
            chip.text = size.name
            chip.setOnClickListener {
                Log.d("FEO33", "You clicked ${size.name}")
            }

            // some guy said
            // yeah, can add chips to group with  chipGroup.addView(chip);
            // please note that you should add id to each chip in order
            // to some functionality of group work perfectly
            // (e.g singleSelection="true" )
            // chip.setId(ViewCompat.generateViewId());
            // probably not needed but might be worth checking later
            chipGroupSizes.addView(chip)
        }





        // COLORS





        val chipGroupColors = view.findViewById<ChipGroup>(R.id.chipGroupColors)
        val colors = mutableListOf(
            Color(1, "light sea green", "#17C3B2"),
            Color(2, "CG Blue", "#227C9D"),
            Color(3, "maximum yellow red", "#FFCB77")
        )
        colors.forEach { color ->

//            val chip = LayoutInflater.from(context).inflate(R.layout.product_color_chip, null) as Chip
//            val chip = Chip(context, null, R.style.ChipColor)
//            chip.text = "text"


//            val colorParsed = android.graphics.Color.parseColor(color.rgbHex)
//
//            val states = arrayOf(
//                intArrayOf(android.R.attr.state_enabled),
//                intArrayOf(-android.R.attr.state_enabled),
//                intArrayOf(-android.R.attr.state_checked),
//                intArrayOf(android.R.attr.state_pressed)
//            )
//
//            val colorss = intArrayOf(
//                colorParsed,
//                adjustAlpha(colorParsed, 0.30f),
//                colorParsed,
//                colorParsed
//            )
//
//            chip.chipBackgroundColor = ColorStateList(states, colorss)
//            chip.width = 300
//            chip.height = 300
            chipGroupColors.addView(chip)

////            chip.text = "ffff "
////            chip.setOnClickListener {
////                Log.d("FEO33", "You clicked ${color.name}")
////            }
////            chip.width = 400
//            chipGroupColors.addView(chip)
        }
//        chipGroupColors.requestLayout()

        return view
    }



}