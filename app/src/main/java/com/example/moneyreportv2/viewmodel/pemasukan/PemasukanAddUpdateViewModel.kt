package com.example.moneyreportv2.viewmodel.pemasukan

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.pemasukan.Pemasukan
import com.example.moneyreportv2.database.pemasukan.PemasukanRepository

class PemasukanAddUpdateViewModel(application: Application): ViewModel() {
    private val mRepository: PemasukanRepository = PemasukanRepository(application)

    fun insert(pemasukan: Pemasukan){
        mRepository.insertPemasukan(pemasukan)
    }

    fun update(pemasukan: Pemasukan){
        mRepository.updatePemasukan(pemasukan)
    }

    fun delete(pemasukan: Pemasukan){
        mRepository.deletePemasukan(pemasukan)
    }
}