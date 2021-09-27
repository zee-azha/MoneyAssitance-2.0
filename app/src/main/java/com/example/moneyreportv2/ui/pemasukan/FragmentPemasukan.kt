package com.example.moneyreportv2.ui.pemasukan


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneyreportv2.R
import com.example.moneyreportv2.databinding.FragmentPemasukanBinding
import com.example.moneyreportv2.helper.pemasukan.PemasukanAdapter
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanMainViewModel
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanViewModelFactory

class FragmentPemasukan : Fragment(R.layout.fragment_pemasukan){

    private var _activityMainBinding : FragmentPemasukanBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: PemasukanAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _activityMainBinding= FragmentPemasukanBinding.bind(view)

        adapter = PemasukanAdapter()
        binding?.rvPemasukan?.layoutManager = LinearLayoutManager(activity)
        binding?.rvPemasukan?.setHasFixedSize(true)
        binding?.rvPemasukan?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(activity, PemasukanAddUpdate::class.java)
                startActivity(intent)
            }
        }

        val mainViewModel = obtainViewModel(activity as AppCompatActivity)
        mainViewModel.getPemasukan().observe(viewLifecycleOwner,{pemasukanList->
        if (pemasukanList != null){
            adapter.setListPemasukan(pemasukanList)
        }
        })
    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): PemasukanMainViewModel {
        val factory = PemasukanViewModelFactory.getInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity,factory).get(PemasukanMainViewModel::class.java)


    }


}