package com.marekpdev.shoppingapp.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter
import com.marekpdev.shoppingapp.views.ChipsHelper

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class ProductFragment : Fragment() {

    private val productId = 1L

    private lateinit var binding: FragmentProductBinding

    private val sizesViewMappings = mutableMapOf<Size, Chip>()
    private val colorsViewMappings = mutableMapOf<Color, Chip>()

    private val navArgs: ProductFragmentArgs by navArgs()

    private val viewModel: ProductViewModel by viewModels { ProductViewModelFactory(navArgs.productId) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        binding.productViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.productAddedEvent.observe(viewLifecycleOwner) {
            // move to a different frag
        }

        initLayout(binding)

        return binding.root
    }

    private fun initLayout(binding: FragmentProductBinding) = binding.apply {
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

            // COLORS
            chipGroupColors.setOnCheckedChangeListener { group, checkedId ->
                Log.d("FEO33", "Checked changed")
            }

            viewModel.product.observe(viewLifecycleOwner) { product ->
                // SIZES
                chipGroupSizes.removeAllViews()
                product.availableSizes.forEach { size ->
                    ChipsHelper.createChip(
                        requireContext(),
                        size
                    ).also { chip ->
                        sizesViewMappings[size] = chip
                        chip.setOnClickListener { viewModel.selectSize(size) }
                        chipGroupSizes.addView(chip)
                    }
                }

                // COLORS
                chipGroupColors.removeAllViews()
                product.availableColors.forEach { color ->
                    ChipsHelper.createChip(
                        requireContext(),
                        color
                    ).also { chip ->
                        colorsViewMappings[color] = chip
                        chip.setOnClickListener { viewModel.selectColor(color) }
                        chipGroupColors.addView(chip)
                    }
                }
            }

            btnAddProduct.setOnClickListener {
                viewModel.addProduct()
            }
        }

    }

}