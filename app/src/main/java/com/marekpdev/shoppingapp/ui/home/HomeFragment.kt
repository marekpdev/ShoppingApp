package com.marekpdev.shoppingapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentHomeBinding
import com.marekpdev.shoppingapp.databinding.FragmentProductBinding

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        binding.apply {
            lifecycleOwner = this@HomeFragment
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentHomeBinding) = binding.apply {
        rvHomeContent.layoutManager = LinearLayoutManager(context)
        rvHomeContent.adapter = HomeContentRVAdapter()
    }

}