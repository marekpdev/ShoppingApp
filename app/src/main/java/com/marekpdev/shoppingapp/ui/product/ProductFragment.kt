package com.marekpdev.shoppingapp.ui.product

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.repository.Data
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import com.marekpdev.shoppingapp.views.ChipsHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class ProductFragment : Fragment(), MviView<ProductState, ProductCommand> {

    private lateinit var binding: FragmentProductBinding
    private val navArgs: ProductFragmentArgs by navArgs()

    private val sizesViewMappings = mutableMapOf<Size, Chip>()
    private val colorsViewMappings = mutableMapOf<Color, Chip>()

    @Inject
    lateinit var productViewModelFactory: ProductViewModel.Factory

    private val viewModel by viewModels<ProductViewModel> {
        ProductViewModel.provideFactory(
            assistedFactory = productViewModelFactory,
            productId = navArgs.productId
        )
    }

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
//
        binding.apply {
            lifecycleOwner = this@ProductFragment
            initLayout(this)
        }


        // TODO need to remove it and find a better way
        // beause we are using viewModel by lazy then
        // the 'init' of view model is not being called and hence
        // productStore.dispatch(ProductAction.FetchProduct(productId)) doesnt work
        viewModel.toString()

    }

    private fun initLayout(binding: FragmentProductBinding) = binding.apply {
        Log.d("FEO401", "INIT LAYOUT")
        viewModel.bind(viewLifecycleOwner, this@ProductFragment)

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

        btnAddProduct.setOnClickListener {
            viewModel.dispatch(ProductAction.AddProductClicked)
        }

        productCard.apply {
            (btnAddProduct.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                StickyBottomBehavior(btnAddProductAnchor, resources.getDimensionPixelOffset(R.dimen.btn_add_product_margins))
        }
    }

    override fun render(state: ProductState) {
        // there was an issue with clipping when padding == 0
        // (words were going beyond the shape) - for the moment it has been fixed
        // by just applying padding == 16 but we might look at it later on if needed
//            val scrollViewProductCard = view.findViewById<NestedScrollView>(R.id.scrollViewProductCard)
//        scrollViewProductCard.outlineProvider = ViewOutlineProvider.PADDED_BOUNDS
//        scrollViewProductCard.clipToOutline = true

        Log.d("FEO401", "Render $state")
        binding.apply {
            // TODO not sure if it will work correctly
            vpProductImages.adapter = ImagesAdapter(state.product?.images ?: emptyList())
            // TODO not sure if it will work correctly
            TabLayoutMediator(tlProductImages, vpProductImages) { tab, position ->}.attach()

            productCard.apply {

                tvName.text = state.product?.name
                tvPrice.text = "$${state.product?.price}"
                tvDescription.text = state.product?.description

                // SIZES
                if(chipGroupSizes.childCount == 0){
                    chipGroupSizes.removeAllViews()
                    sizesViewMappings.clear()
                    state.product?.availableSizes?.forEach { size ->
                        ChipsHelper.createChip(
                            requireContext(),
                            size
                        ).also { chip ->
                            sizesViewMappings[size] = chip
                            chip.setOnClickListener {
                                viewModel.dispatch(ProductAction.SizeSelected(size))
                            }
                            chipGroupSizes.addView(chip)
                        }
                    }
                }

                sizesViewMappings.forEach { (size, chip) ->
                    chip.isChecked = size == state.selectedSize
                }

                // COLORS
                if(chipGroupColors.childCount == 0){
                    chipGroupColors.removeAllViews()
                    colorsViewMappings.clear()
                    state.product?.availableColors?.forEach { color ->
                        ChipsHelper.createChip(
                            requireContext(),
                            color
                        ).also { chip ->
                            colorsViewMappings[color] = chip
                            chip.setOnClickListener {
                                viewModel.dispatch(ProductAction.ColorSelected(color))
                            }
                            chipGroupColors.addView(chip)
                        }
                    }
                }

                colorsViewMappings.forEach { (color, chip) ->
                    chip.isChecked = color == state.selectedColor
                }
            }

        }
    }

    override fun onCommand(command: ProductCommand) {
        when(command){
            is ProductCommand.ProductAddedToBasket -> Toast.makeText(requireContext(), "Product added to basket", Toast.LENGTH_SHORT).show()
        }
    }
}