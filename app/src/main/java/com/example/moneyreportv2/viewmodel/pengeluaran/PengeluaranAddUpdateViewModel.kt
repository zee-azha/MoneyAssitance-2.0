package com.example.moneyreportv2.viewmodel.pengeluaran

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.database.LaporanRepository


class PengeluaranAddUpdateViewModel(application: Application): ViewModel() {
    private val mRepository: LaporanRepository = LaporanRepository(application)

    fun insert(laporan: Laporan){
        mRepository.insert(laporan)
    }

    fun update(laporan: Laporan){
        mRepository.update(laporan)
    }

    fun delete(laporan: Laporan ){
        mRepository.delete(laporan)
    }
}