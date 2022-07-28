package com.marekpdev.shoppingapp.ui.product

import android.view.View
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.product.images.ImagesAdapter
import com.marekpdev.shoppingapp.views.ChipsHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// https://medium.com/@sreeharikv112/create-introduction-screen-with-viewpager2-and-circle-indicators-no-custom-library-please-68d5b1fec8b1
// https://github.com/sreeharikv112/ViewPagerIndicator
/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class ProductFragment : BaseFragment<ProductState, ProductAction, ProductCommand, FragmentProductBinding>(R.layout.fragment_product) {

    private val navArgs: ProductFragmentArgs by navArgs()

    private val sizesViewMappings = mutableMapOf<Size, Chip>()
    private val colorsViewMappings = mutableMapOf<Color, Chip>()

    private val imagesAdapter = ImagesAdapter()

    @Inject
    lateinit var productViewModelFactory: ProductViewModel.Factory

    override val viewModel by viewModels<ProductViewModel> {
        ProductViewModel.provideFactory(
            assistedFactory = productViewModelFactory,
            productId = navArgs.productId
        )
    }

    // TODO
    // issue with
    // open ProductFragment with product1
    // loading product for 2 sec (need to change loading time in ProductsRepositoryImpl)
    // close ProductFragment
    // open ProductFragment with product2
    // the product1 is still being shown and after 2 seconds we can see product2

    override fun initLayout(binding: FragmentProductBinding) = with(binding) {
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.share -> {
                    // Handle favorite icon press
                    true
                }
                R.id.favorite -> {
                    // Handle favorite icon press
                    true
                }
                else -> false
            }
        }

        btnAddProduct.setOnClickListener {
            viewModel.dispatch(ProductAction.AddProductClicked)
        }

        vpProductImages.adapter = imagesAdapter
        TabLayoutMediator(tlProductImages, vpProductImages) { tab, position ->}.attach()

        productCard.apply {
            (btnAddProduct.layoutParams as CoordinatorLayout.LayoutParams).behavior =
                StickyBottomBehavior(btnAddProductAnchor, resources.getDimensionPixelOffset(R.dimen.btn_add_product_margins))
        }

        return@with
    }

    override fun render(state: ProductState) {
        // there was an issue with clipping when padding == 0
        // (words were going beyond the shape) - for the moment it has been fixed
        // by just applying padding == 16 but we might look at it later on if needed
//            val scrollViewProductCard = view.findViewById<NestedScrollView>(R.id.scrollViewProductCard)
//        scrollViewProductCard.outlineProvider = ViewOutlineProvider.PADDED_BOUNDS
//        scrollViewProductCard.clipToOutline = true

        binding.apply {

            imagesAdapter.setData(state.product?.images ?: listOf())

            productCard.apply {

                val (contentVisibility, progressBarVisibility) = when (state.loading){
                    true -> View.VISIBLE to View.GONE
                    else -> View.VISIBLE to View.GONE
                }

                contentVisibility.also {
                    tvName.visibility = it
                    tvPrice.visibility = it
                    tvDescription.visibility = it
                    vDivider1.visibility = it
                    tvSelectSize.visibility = it
                    horizontalScrollViewSizes.visibility = it
                    chipGroupSizes.visibility = it
                    vDivider2.visibility = it
                    tvSelectColor.visibility = it
                    horizontalScrollViewColors.visibility = it
                    chipGroupColors.visibility = it
                    btnAddProductAnchor.visibility = it
                    btnAddProduct.visibility = it
                    tvYouMightAlsoLike.visibility = it
                }

                progressBarVisibility.also {
                    pbProductImagesLoading.visibility = it
                    pbContentLoading.visibility = it
                }

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
            is ProductCommand.ProductAddedToBasket -> {
                Toast.makeText(requireContext(), "Product added to basket", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }
}