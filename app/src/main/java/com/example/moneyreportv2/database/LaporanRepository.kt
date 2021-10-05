package com.example.moneyreportv2.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors



class LaporanRepository(application: Application) {
    private val mLaporanDao: LaporanDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = LaporanRoomDatabase.getDatabase(application)
        mLaporanDao = db.pemasukanDao()
    }

    fun getPemasukan():LiveData<List<Laporan>> = mLaporanDao.getPemasukan()

    fun getPengeluaran():LiveData<List<Laporan>> = mLaporanDao.getPengeluaran()

    fun insert(laporan: Laporan){
        executorService.execute {
            mLaporanDao.insert(laporan)
        }
    }

    fun delete(laporan: Laporan){
        executorService.execute {
            mLaporanDao.delete(laporan)
        }
    }

    fun update(laporan: Laporan){
        executorService.execute {
            mLaporanDao.update(laporan)
        }
    }

    fun getPemasukanByDate(date: String):LiveData<List<Laporan>> = mLaporanDao.getPemasukanByDate(date)

    fun getPengeluaranByDate(date: String):LiveData<List<Laporan>> = mLaporanDao.getPengeluaranByDate(date)

    fun getLaporan():LiveData<List<Laporan>> = mLaporanDao.getLaporan()

    fun getTotal():LiveData<List<Laporan>> = mLaporanDao.getTotal()

}