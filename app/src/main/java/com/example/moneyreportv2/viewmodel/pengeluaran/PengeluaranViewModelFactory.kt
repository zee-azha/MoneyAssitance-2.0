package com.example.moneyreportv2.viewmodel.pengeluaran

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PengeluaranViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var INSTANCE: PengeluaranViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): PengeluaranViewModelFactory{
        if (INSTANCE == null){
            synchronized(PengeluaranViewModelFactory::class.java){
                INSTANCE = PengeluaranViewModelFactory(application)

            }
        }
            return INSTANCE as PengeluaranViewModelFactory
        }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PengeluaranMainViewModel::class.java)){
            return PengeluaranMainViewModel(mApplication) as T
        }else if(modelClass.isAssignableFrom(PengeluaranAddUpdateViewModel::class.java)){
            return PengeluaranAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}