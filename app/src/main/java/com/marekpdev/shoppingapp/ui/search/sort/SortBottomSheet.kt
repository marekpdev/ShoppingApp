package com.marekpdev.shoppingapp.ui.search.sort

import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetSortBinding
import com.marekpdev.shoppingapp.ui.base.BaseBottomSheetDialogFragment
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
@AndroidEntryPoint
class SortBottomSheet: BaseBottomSheetDialogFragment<SearchState, SearchAction, SearchCommand, BottomSheetSortBinding>(R.layout.bottom_sheet_sort) {

    override val viewModel by viewModels<SortBottomSheetViewModel>()

    override fun initLayout(binding: BottomSheetSortBinding) = with(binding) {

        BottomSheetBehavior.from(binding.llBottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

        rbPriceLowestFirst.setOnClickListener {
            viewModel.dispatch(SearchAction.SortSelectedType(SortType.Type.PRICE_LOWEST_FIRST))
        }
        rbPriceHighestFirst.setOnClickListener {
            viewModel.dispatch(SearchAction.SortSelectedType(SortType.Type.PRICE_HIGHEST_FIRST))
        }

        btnConfirmSort.setOnClickListener {
            viewModel.dispatch(SearchAction.SortConfirmed)
        }
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun render(state: SearchState) {
        binding.apply {
            when(state.sortType.type.selected) {
                SortType.Type.PRICE_LOWEST_FIRST -> { rbPriceLowestFirst.isChecked = true }
                SortType.Type.PRICE_HIGHEST_FIRST -> { rbPriceHighestFirst.isChecked = true }
            }
        }
    }

    override fun onCommand(command: SearchCommand) {
        when(command){
            is SearchCommand.HideSortBottomSheet -> dismiss()
        }
    }


}