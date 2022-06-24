package com.marekpdev.shoppingapp.ui.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter
import com.marekpdev.shoppingapp.views.ChipsHelper

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class ProductFragment : Fragment() {

    private lateinit var binding: FragmentProductBinding
    private val navArgs: ProductFragmentArgs by navArgs()

    private val sizesViewMappings = mutableMapOf<Size, Chip>()
    private val colorsViewMappings = mutableMapOf<Color, Chip>()

    // todo what about injecting other dependencies in ProductViewModel that should be provided by dagger?
//    private val viewModel: ProductViewModel by viewModels { ProductViewModelFactory(navArgs.productId) }
//    private val viewModel: ProductViewModel by viewModels()

//    private lateinit var viewModel: ProductViewModel
//    private lateinit var viewModelFactory: ProductViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("FEO36", "product fragment")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = navArgs.productId
//
//        viewModelFactory = ProductViewModelFactory(productId)
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProductViewModel::class.java)
//
        binding.apply {
            lifecycleOwner = this@ProductFragment
//            productViewModel = viewModel
            initLayout(this)
        }
//
//        viewModel.productAddedEvent.observe(viewLifecycleOwner) {
//            // move to a different frag
//        }
    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        (requireActivity().application as AppComponentProvider).appComponent.inject(this)
//    }

    private fun initLayout(binding: FragmentProductBinding) = binding.apply {
        val product = Data.getProduct(1, 1)
        vpProductImages.adapter = ImagesAdapter(product.images)

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


            //var desc = ""
            //(1..2).forEach { desc += "this is the very second line of $it" }
            //tvDescription.setText(desc)

            // SIZES
            chipGroupSizes.setOnCheckedChangeListener { group, checkedId ->
                Log.d("FEO33", "Checked changed")
            }

            // COLORS
            chipGroupColors.setOnCheckedChangeListener { group, checkedId ->
                Log.d("FEO33", "Checked changed")
            }

           //viewModel.product.observe(viewLifecycleOwner) { product ->
                // SIZES
            val product = Data.getProduct(1L, 1)
                chipGroupSizes.removeAllViews()
                product.availableSizes.forEach { size ->
                    ChipsHelper.createChip(
                        requireContext(),
                        size
                    ).also { chip ->
                        sizesViewMappings[size] = chip
                        chip.setOnClickListener {
//                            viewModel.selectSize(size)
                        }
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
                        chip.setOnClickListener {
//                            viewModel.selectColor(color)
                        }
                        chipGroupColors.addView(chip)
                    }
                }
           // }

            btnAddProduct.setOnClickListener {
//                viewModel.addProduct()
            }

            (btnAddProduct.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                StickyBottomBehavior(btnAddProductAnchor, resources.getDimensionPixelOffset(R.dimen.btn_add_product_margins));

        }

    }

}