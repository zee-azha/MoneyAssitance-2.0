package com.example.moneyreportv2.database.pemasukan

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.TypeConverters
import com.example.moneyreportv2.util.DateConverter
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class PemasukanRepository(application: Application) {
    private val mPemasukanDao: PemasukanDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PemasukanRoomDatabase.getDatabase(application)
        mPemasukanDao = db.pemasukanDao()
    }

    fun getPemasukan():LiveData<List<Pemasukan>> = mPemasukanDao.getPemasukan()

    fun insertPemasukan(pemasukan: Pemasukan){
        executorService.execute {
            mPemasukanDao.insert(pemasukan)
        }
    }
    fun deletePemasukan(pemasukan: Pemasukan){
        executorService.execute {
            mPemasukanDao.delete(pemasukan)
        }
    }
    fun updatePemasukan(pemasukan: Pemasukan){
        executorService.execute {
            mPemasukanDao.update(pemasukan)
        }
    }
}