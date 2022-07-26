package com.marekpdev.shoppingapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
abstract class BaseBottomSheetDialogFragment<S: State, A: Action, C: Command, B: ViewDataBinding>(@LayoutRes val contentLayoutId: Int):
    BottomSheetDialogFragment(), MviView<S, C>  {

    protected lateinit var binding: B

    abstract val viewModel: BaseViewModel<S, A, C>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // https://developer.android.com/codelabs/kotlin-android-training-live-data#4
        // here there is some info so might need to add observers in onCreateView instead
//        Why use viewLifecycleOwner?
//        Fragment views get destroyed when a user navigates away from a fragment,
//        even though the fragment itself is not destroyed. This essentially creates
//        two lifecycles, the lifecycle of the fragment, and the lifecycle of the
//        fragment's view. Referring to the fragment's lifecycle instead of the
//        fragment view's lifecycle can cause subtle bugs when updating the fragment's
//        view. Therefore, when setting up observers that affect the fragment's view you should:
//        1. Set up the observers in onCreateView()
//        2. Pass in viewLifecycleOwner to observers

        binding.apply {
            lifecycleOwner = this@BaseBottomSheetDialogFragment
            viewModel.bind(viewLifecycleOwner, this@BaseBottomSheetDialogFragment)
            initLayout(this)
        }
    }

    protected abstract fun initLayout(binding: B)

    abstract override fun render(state: S)

    abstract override fun onCommand(command: C)
}