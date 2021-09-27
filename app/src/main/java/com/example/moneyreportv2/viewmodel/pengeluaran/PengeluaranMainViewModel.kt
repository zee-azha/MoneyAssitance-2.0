package com.example.moneyreportv2.viewmodel.pengeluaran

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.pengeluaran.Pengeluaran
import com.example.moneyreportv2.database.pengeluaran.PengeluaranRepository
import java.util.*

class PengeluaranMainViewModel(application: Application): ViewModel() {
    private val mRepository: PengeluaranRepository = PengeluaranRepository(application)

    fun getPengeluaran(): LiveData<List<Pengeluaran>> = mRepository.getPengeluaran()
}