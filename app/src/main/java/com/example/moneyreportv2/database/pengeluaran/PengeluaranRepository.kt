package com.example.moneyreportv2.database.pengeluaran

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.moneyreportv2.database.pemasukan.Pemasukan

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PengeluaranRepository (application: Application) {
    private val mPengeluaranDao: PengeluaranDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PengeluaranRoomDatabase.getDatabase(application)
        mPengeluaranDao = db.PengeluaranDao()
    }

    fun getPengeluaran(): LiveData<List<Pengeluaran>> = mPengeluaranDao.getPengeluaran()

    fun insertPengeluaran(pengeluaran: Pengeluaran) {
        executorService.execute {
            mPengeluaranDao.insertPengeluaran(pengeluaran)
        }
    }

    fun deletePengeluaran(pengeluaran: Pengeluaran) {
        executorService.execute {
            mPengeluaranDao.delete(pengeluaran)
        }
    }

    fun updatePengeluaran(pengeluaran: Pengeluaran) {
        executorService.execute {
            mPengeluaranDao.update(pengeluaran)
        }
    }
    fun getPengeluaranByDate(date: String):LiveData<List<Pengeluaran>> = mPengeluaranDao.getPengeluaranByDate(date)
}
