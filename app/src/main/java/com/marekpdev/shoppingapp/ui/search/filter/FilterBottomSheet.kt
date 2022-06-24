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
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetFilterBinding
import com.marekpdev.shoppingapp.databinding.BottomSheetSortBinding
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.mvi.MviView
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
class FilterBottomSheet: BottomSheetDialogFragment(), MviView<SearchState, SearchCommand> {

    private val viewModel by viewModels<FilterBottomSheetViewModel>()

    private lateinit var binding: BottomSheetFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_filter, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@FilterBottomSheet
            initLayout(this)
        }


    }

    private fun initLayout(binding: BottomSheetFilterBinding) = binding.apply {
        viewModel.bind(viewLifecycleOwner, this@FilterBottomSheet)

        BottomSheetBehavior.from(binding.llBottomSheet).state = BottomSheetBehavior.STATE_EXPANDED

        rangeSliderPrice.valueFrom = 0f
        rangeSliderPrice.valueTo = 100f
        rangeSliderPrice.values = listOf(10f, 30f)
    }

    companion object {
        const val TAG = "ModalBottomSheetFilter"
    }

    override fun render(state: SearchState) {
        binding.apply {
            Log.d("FEO60", "FILTER Current state is $state")
        }
    }

    override fun onCommand(command: SearchCommand) {

    }


}