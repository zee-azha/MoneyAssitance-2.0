package com.example.moneyreportv2.viewmodel.pengeluaran

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.database.LaporanRepository
import java.util.*

class PengeluaranMainViewModel(application: Application): ViewModel() {
    private val mRepository: LaporanRepository   = LaporanRepository(application)

    fun getPengeluaran(): LiveData<List<Laporan>> = mRepository.getPengeluaran()

    fun getPengeluaranByDate(date: String): LiveData  <List<Laporan>> = mRepository.getPengeluaranByDate(date)
}