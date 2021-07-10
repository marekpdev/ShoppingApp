package com.marekpdev.shoppingapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marekpdev.shoppingapp.R
import com.marekpdev.shoppingapp.api.RetrofitProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Created by Marek Pszczolka on 14/04/2021.
 */
class SearchFragment : Fragment() {

    private val adapter = ProductsRVAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

//        view.findViewById<Button>(R.id.signup_btn).setOnClickListener {
//            findNavController().navigate(R.id.action_register_to_registered)
//        }

        view.findViewById<Button>(R.id.btnGetAllProducts).setOnClickListener {
            getAllProducts()
        }

        val rvProducts = view.findViewById<RecyclerView>(R.id.rvProducts)
        rvProducts.layoutManager = LinearLayoutManager(context)
        rvProducts.adapter = adapter

        return view
    }

    fun getAllProducts(){
        RetrofitProvider.getApi().getAllProducts().
                subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                Log.d("FEO33", "Got 1products!")

            }
            .doOnError {
                Log.d("FEO33", "Got 1ERROR! $it")
            }
            .subscribe(
                {
                    Log.d("FEO33", "Got 2products!")
                    adapter.setData(it.products)
                },
                {
                    Log.d("FEO33", "Got 2ERROR! $it")
                }
            )
    }
}