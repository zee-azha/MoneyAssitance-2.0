package com.example.moneyreportv2.viewmodel.pemasukan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.database.LaporanRepository

class PemasukanMainViewModel(application: Application): ViewModel() {
    private val mRepository: LaporanRepository = LaporanRepository(application)

    fun getPemasukan(): LiveData<List<Laporan>> = mRepository.getPemasukan()

    fun getPemasukanByDate(date: String): LiveData  <List<Laporan>> = mRepository.getPemasukanByDate(date)

}