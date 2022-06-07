package com.marekpdev.shoppingapp.ui.search.sort

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.BottomSheetSortBinding
import com.marekpdev.shoppingapp.databinding.FragmentSearchBinding
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.ui.search.SearchCommand
import com.marekpdev.shoppingapp.ui.search.SearchState
import com.marekpdev.shoppingapp.ui.search.SearchViewModel
import java.lang.StringBuilder

/**
 * Created by Marek Pszczolka on 07/06/2022.
 */
class SortBottomSheet: BottomSheetDialogFragment(), MviView<SearchState, SearchCommand> {

    private val viewModel: SortBottomSheetViewModel by lazy {
        ViewModelProviders.of(this).get(SortBottomSheetViewModel::class.java)
    }

    private lateinit var binding: BottomSheetSortBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_sort, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@SortBottomSheet
            initLayout(this)
        }
    }

    private fun initLayout(binding: BottomSheetSortBinding) = binding.apply {
        viewModel.bind(viewLifecycleOwner, this@SortBottomSheet)

        val sb = StringBuilder()
        (1..20).forEachIndexed { index, number ->
            sb.append("$index some really really nice text \n")
        }
        tvModal.text = sb.toString()
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun render(state: SearchState) {
        binding.apply {
            Log.d("FEO50", "Current state is $state")
        }
    }

    override fun onCommand(command: SearchCommand) {

    }


}