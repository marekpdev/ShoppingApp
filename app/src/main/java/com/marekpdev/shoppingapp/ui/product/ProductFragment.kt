package com.marekpdev.shoppingapp.ui.product

import android.content.ContextWrapper
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Product
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter
import com.marekpdev.shoppingapp.views.ChipsHelper

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class ProductFragment : Fragment() {

    private val product: Product = createProduct(1)

    private lateinit var binding: FragmentProductBinding

    private val onSizeClicked: (Chip, Size) -> Unit = { chip, size ->
        Log.d("FEO33", "Clicked ${chip.id} ${size.name} ${chip.isChecked}")
    }

    private val onColorClicked: (Color) -> Unit = { color ->
        Log.d("FEO33", "Clicked ${color.name}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentProductBinding.inflate(inflater, container, false).also {
            binding = it
            initLayout()
        }.root
    }

    private fun initLayout() = binding.apply {
        vpProductImages.adapter = ImagesAdapter(
            listOf(
                R.drawable.product1,
                R.drawable.product2,
                R.drawable.product3,
                R.drawable.product4,
            )
        )

        TabLayoutMediator(tlProductImages, vpProductImages) { tab, position ->}.attach()

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

        // there was an issue with clipping when padding == 0
        // (words were going beyond the shape) - for the moment it has been fixed
        // by just applying padding == 16 but we might look at it later on if needed
//            val scrollViewProductCard = view.findViewById<NestedScrollView>(R.id.scrollViewProductCard)
//        scrollViewProductCard.outlineProvider = ViewOutlineProvider.PADDED_BOUNDS
//        scrollViewProductCard.clipToOutline = true

        productCard.apply {

            // SIZES
            chipGroupSizes.setOnCheckedChangeListener { group, checkedId ->
                Log.d("FEO33", "Checked changed")
            }

            product.availableSizes.forEach { size ->
                    ChipsHelper.createChip(
                        requireContext(),
                        size
                    ).also { chip ->
                        chip.setOnClickListener { onSizeClicked(it as Chip, size) }
                        chipGroupSizes.addView(chip)
                    }
                }

            // COLORS
            product.availableColors.forEach { color ->
                ChipsHelper.createChip(
                    requireContext(),
                    color
                ).also { chip ->
                    chip.setOnClickListener { onColorClicked(color) }
                    chipGroupColors.addView(chip)
                }
            }
        }

    }


    // DEBUG
    private fun createProduct(id: Long) = Product(
        id = id,
        name = "Product $id",
        description = "Pinstripped cornflower blue cotton blouse takes you on a walk to the park or just down the hall.",
        price = id * 1.11,
        oldPrice = id * 1.44,
        currency = "$",
        availableColors = listOf(
            Color(1, "light sea green", "#17C3B2"),
            Color(2, "CG Blue", "#227C9D"),
            Color(3, "maximum yellow red", "#FFCB77")
        ),
        availableSizes = (1..9).map { Size(it, "0$it") },
        images = listOf(
            "https://cdn.shopify.com/s/files/1/0932/1794/files/Artboard_24_370x230@2x.png?v=1605584742",
            "https://romans-cdn.rlab.net/images/extralarge/350f24c0-9476-465b-9c8a-5e0a70b6bf62.jpg",
            "https://cdn.foreverunique.com/products/uar212729_4205.jpg?h=480&w=",
            "https://media.sezane.com/image/upload/c_crop,fl_progressive:semi,h_0.95333333333333,q_auto:best,w_1,x_0,y_0.023333333333333/c_scale,w_598/whb5logixhjjmnqrurfp.jpg"
        )
    )

}