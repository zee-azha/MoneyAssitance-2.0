package com.example.moneyreportv2.viewmodel.laporan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class LaporanViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var INSTANCE: LaporanViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): LaporanViewModelFactory{
        if (INSTANCE == null){
            synchronized(LaporanViewModelFactory::class.java){
                INSTANCE = LaporanViewModelFactory(application)

            }
        }
            return INSTANCE as LaporanViewModelFactory
        }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LaporanMainViewModel::class.java)){
            return LaporanMainViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}