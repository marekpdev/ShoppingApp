package com.marekpdev.shoppingapp.ui.registration

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.databinding.FragmentLoginBinding
import com.marekpdev.shoppingapp.databinding.FragmentRegistrationBinding

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_registration, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = this@RegistrationFragment
            initLayout(this)
        }
    }

    private fun initLayout(binding: FragmentRegistrationBinding) = binding.apply {
        Log.d("FEO33", "initLayout")
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

    }

}