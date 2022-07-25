package com.marekpdev.shoppingapp.ui.search.sort

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
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetFilterBinding
import com.marekpdev.shoppingapp.databinding.BottomSheetSortBinding
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.ui.base.BaseBottomSheetDialogFragment
import com.marekpdev.shoppingapp.ui.search.SearchAction
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

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