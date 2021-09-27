package com.example.moneyreportv2.viewmodel.pemasukan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moneyreportv2.database.pemasukan.Pemasukan
import com.example.moneyreportv2.database.pemasukan.PemasukanRepository
import java.util.*

class PemasukanMainViewModel(application: Application): ViewModel() {
    private val mRepository: PemasukanRepository = PemasukanRepository(application)

    fun getPemasukan(): LiveData<List<Pemasukan>> = mRepository.getPemasukan()

    fun getPemasukanByDate(date: String): LiveData  <List<Pemasukan>> = mRepository.getPemasukanByDate(date)
}