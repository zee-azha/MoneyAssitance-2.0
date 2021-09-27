package com.example.moneyreportv2.viewmodel.pemasukan

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class PemasukanViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var INSTANCE: PemasukanViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): PemasukanViewModelFactory{
        if (INSTANCE == null){
            synchronized(PemasukanViewModelFactory::class.java){
                INSTANCE = PemasukanViewModelFactory(application)

            }
        }
            return INSTANCE as PemasukanViewModelFactory
        }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PemasukanMainViewModel::class.java)){
            return PemasukanMainViewModel(mApplication) as T
        }else if(modelClass.isAssignableFrom(PemasukanAddUpdateViewModel::class.java)){
            return PemasukanAddUpdateViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}