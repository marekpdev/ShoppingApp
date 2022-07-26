package com.marekpdev.shoppingapp.ui.search.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetFilterBinding
import com.marekpdev.shoppingapp.databinding.BottomSheetSortBinding
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.models.Color
import com.marekpdev.shoppingapp.models.Size
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.ui.base.BaseBottomSheetDialogFragment
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import com.marekpdev.shoppingapp.views.ChipsHelper
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder
import kotlin.math.abs

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
@AndroidEntryPoint
class FilterBottomSheet: BaseBottomSheetDialogFragment<SearchState, SearchAction, SearchCommand, BottomSheetFilterBinding>(R.layout.bottom_sheet_filter) {

    companion object {
        const val TAG = "ModalBottomSheetFilter"
        private const val MIN_SEPARATION_THRESHOLD = 5f
        private const val MIN_SEPARATION_VALUE = 2f
    }

    override val viewModel by viewModels<FilterBottomSheetViewModel>()

    private val sizesViewMappings = mutableMapOf<Size, Chip>()
    private val colorsViewMappings = mutableMapOf<Color, Chip>()

    override fun initLayout(binding: BottomSheetFilterBinding) = with(binding) {
        BottomSheetBehavior.from(binding.llBottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

        btnConfirmFilter.setOnClickListener {
            viewModel.dispatch(SearchAction.FilterConfirmed)
        }

        rangeSliderPrice.addOnChangeListener { slider, value, fromUser ->
            val minPrice = rangeSliderPrice.values[0].toInt()
            val maxPrice = rangeSliderPrice.values[1].toInt()
            viewModel.dispatch(SearchAction.FilterSelectedPriceRangeChanged(IntRange(minPrice, maxPrice)))
        }
    }

    override fun render(state: SearchState) {
        binding.apply {
            state.filters?.let { filters ->

                rangeSliderPrice.valueFrom = filters.priceRange.available.first.toFloat()
                rangeSliderPrice.valueTo = filters.priceRange.available.last.toFloat()
                rangeSliderPrice.stepSize = 1f
                if(abs(rangeSliderPrice.valueTo - rangeSliderPrice.valueFrom) > MIN_SEPARATION_THRESHOLD){
                    rangeSliderPrice.setMinSeparationValue(MIN_SEPARATION_VALUE)
                }

                rangeSliderPrice.values = listOf(
                    filters.priceRange.selected.first.toFloat(),
                    filters.priceRange.selected.last.toFloat()
                )

                // SIZES
                if(chipGroupSizes.childCount == 0) {
                    chipGroupSizes.removeAllViews()
                    sizesViewMappings.clear()
                    filters.sizes.available.forEach { size ->
                        ChipsHelper.createChip(
                            requireContext(),
                            size
                        ).also { chip ->
                            sizesViewMappings[size] = chip
                            chip.setOnClickListener {
                                viewModel.dispatch(SearchAction.FilterSelectedSizeChanged(size))
                            }
                            chipGroupSizes.addView(chip)
                        }
                    }
                }

                sizesViewMappings.forEach { (size, chip) ->
                    chip.isChecked = size in filters.sizes.selected
                }

                // COLORS
                if(chipGroupColors.childCount == 0) {
                    chipGroupColors.removeAllViews()
                    colorsViewMappings.clear()
                    filters.colors.available.forEach { color ->
                        ChipsHelper.createChip(
                            requireContext(),
                            color
                        ).also { chip ->
                            colorsViewMappings[color] = chip
                            chip.setOnClickListener {
                                viewModel.dispatch(SearchAction.FilterSelectedColorChanged(color))
                            }
                            chipGroupColors.addView(chip)
                        }
                    }
                }

                colorsViewMappings.forEach { (color, chip) ->
                    chip.isChecked = color in filters.colors.selected
                }

            }

        }
    }

    override fun onCommand(command: SearchCommand) {
        when(command){
            is SearchCommand.HideFilterBottomSheet -> dismiss()
        }
    }

}