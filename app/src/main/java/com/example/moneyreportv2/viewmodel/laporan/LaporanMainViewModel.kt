package com.example.moneyreportv2.viewmodel.laporan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.database.LaporanRepository

class LaporanMainViewModel(application: Application): ViewModel() {
    private val mRepository: LaporanRepository = LaporanRepository(application)

    fun getLaporan(): LiveData<List<Laporan>> = mRepository.getLaporan()

    fun getTotal(): LiveData<List<Laporan>> = mRepository.getTotal()

}