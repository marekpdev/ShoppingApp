package com.marekpdev.shoppingapp.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.marekpdev.shoppingapp.mvi.Action
import com.marekpdev.shoppingapp.mvi.Command
import com.marekpdev.shoppingapp.mvi.MviView
import com.marekpdev.shoppingapp.mvi.State

/**
 * Created by Marek Pszczolka on 25/07/2022.
 */
abstract class BaseFragment<S: State, A: Action, C: Command, B: ViewDataBinding>(@LayoutRes val contentLayoutId: Int):
    Fragment(), MviView<S, C>  {

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

        binding.apply {
            lifecycleOwner = this@BaseFragment
            viewModel.bind(viewLifecycleOwner, this@BaseFragment)
            initLayout(this)
        }
    }

    protected abstract fun initLayout(binding: B)

    abstract override fun render(state: S)

    abstract override fun onCommand(command: C)
}