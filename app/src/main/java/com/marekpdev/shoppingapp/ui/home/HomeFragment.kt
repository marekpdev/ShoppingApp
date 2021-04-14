package com.marekpdev.shoppingapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marekpdev.shoppingapp.R

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.btnProduct).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_productFragment)
        }

        return view
    }

}