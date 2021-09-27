package com.example.moneyreportv2.viewmodel.pengeluaran

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.pengeluaran.Pengeluaran
import com.example.moneyreportv2.database.pengeluaran.PengeluaranRepository


class PengeluaranAddUpdateViewModel(application: Application): ViewModel() {
    private val mRepository: PengeluaranRepository = PengeluaranRepository(application)

    fun insert(pengeluaran: Pengeluaran){
        mRepository.insertPengeluaran(pengeluaran)
    }

    fun update(Pengeluaran: Pengeluaran){
        mRepository.updatePengeluaran(Pengeluaran)
    }

    fun delete(Pengeluaran: Pengeluaran){
        mRepository.deletePengeluaran(Pengeluaran)
    }
}