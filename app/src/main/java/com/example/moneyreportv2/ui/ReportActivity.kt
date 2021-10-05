package com.example.moneyreportv2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneyreportv2.R
import com.example.moneyreportv2.databinding.ActivityReportBinding
import com.example.moneyreportv2.databinding.FragmentPemasukanBinding
import com.example.moneyreportv2.helper.LaporanAdapter
import com.example.moneyreportv2.helper.PemasukanAdapter
import com.example.moneyreportv2.viewmodel.laporan.LaporanMainViewModel
import com.example.moneyreportv2.viewmodel.laporan.LaporanViewModelFactory

class ReportActivity : AppCompatActivity() {
    private var _activityBinding : ActivityReportBinding? = null
    private val binding get() = _activityBinding
    private lateinit var adapter: LaporanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityBinding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val mainViewModel = obtainViewModel(this@ReportActivity)
        mainViewModel.getLaporan().observe(this,{laporanList ->
            if (laporanList != null){
                adapter.setListLaporan(laporanList)
            }
        })
        adapter = LaporanAdapter()
        binding?.rvLaporan?.layoutManager = LinearLayoutManager(this)
        binding?.rvLaporan?.setHasFixedSize(true)
        binding?.rvLaporan?.adapter = adapter


    }

    private fun obtainViewModel(activity: AppCompatActivity): LaporanMainViewModel {
        val factory = LaporanViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LaporanMainViewModel::class.java)

    }


}