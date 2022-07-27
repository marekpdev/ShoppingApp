package com.marekpdev.shoppingapp.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentAccountBinding
import com.marekpdev.shoppingapp.databinding.FragmentAddressesBinding
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding
import com.marekpdev.shoppingapp.databinding.FragmentSettingsBinding
import com.marekpdev.shoppingapp.rvutils.AdapterDelegatesManager
import com.marekpdev.shoppingapp.rvutils.BaseAdapter
import com.marekpdev.shoppingapp.ui.addresses.*
import com.marekpdev.shoppingapp.ui.addresses.adapters.AddressAdapterDelegate
import com.marekpdev.shoppingapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
@AndroidEntryPoint
class SettingsFragment : BaseFragment<SettingsState, SettingsAction, SettingsCommand, FragmentSettingsBinding>(R.layout.fragment_settings) {

    override val viewModel by viewModels<SettingsViewModel>()

    private val adapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
//            .addDelegate(AddressAdapterDelegate(onAddressClicked))
    )

    override fun initLayout(binding: FragmentSettingsBinding) = with(binding){
        rvSettings.layoutManager = LinearLayoutManager(context)
        rvSettings.adapter = adapter
    }

    override fun render(state: SettingsState) {
        binding.apply {
            pbSettings.visibility = if(state.loading) View.VISIBLE else View.GONE
            adapter.replaceData(state.settings)
        }
    }

    override fun onCommand(command: SettingsCommand) {
        when (command) {
            SettingsCommand.GoBackToAccountScreen -> {
                // todo

            }
        }
    }

}