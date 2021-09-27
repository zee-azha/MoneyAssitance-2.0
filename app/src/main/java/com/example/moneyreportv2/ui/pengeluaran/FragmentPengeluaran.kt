package com.example.moneyreportv2.ui.pengeluaran


import android.content.Intent
import android.os.Bundle

import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneyreportv2.R
import com.example.moneyreportv2.databinding.FragmentPengeluaranBinding
import com.example.moneyreportv2.helper.pengeluaran.PengeluaranAdapter
import com.example.moneyreportv2.viewmodel.pengeluaran.PengeluaranMainViewModel
import com.example.moneyreportv2.viewmodel.pengeluaran.PengeluaranViewModelFactory


class FragmentPengeluaran : Fragment(R.layout.fragment_pengeluaran){

    private var _activityMainBinding : FragmentPengeluaranBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: PengeluaranAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _activityMainBinding= FragmentPengeluaranBinding.bind(view)

        adapter = PengeluaranAdapter()
        binding?.rvPengeluaran?.layoutManager = LinearLayoutManager(activity)
        binding?.rvPengeluaran?.setHasFixedSize(true)
        binding?.rvPengeluaran?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(activity, PengeluaranAddUpdate::class.java)
                startActivity(intent)
            }
        }

        val mainViewModel = obtainViewModel(activity as AppCompatActivity)
        mainViewModel.getPengeluaran().observe(viewLifecycleOwner,{PengeluaranList->
            if (PengeluaranList != null){
                adapter.setListPengeluaran(PengeluaranList)
            }
        })
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): PengeluaranMainViewModel {
        val factory = PengeluaranViewModelFactory.getInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity,factory).get(PengeluaranMainViewModel::class.java)


    }


}