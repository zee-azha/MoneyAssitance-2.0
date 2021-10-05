package com.example.moneyreportv2.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LaporanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(laporan: Laporan)

    @Update
    fun update(laporan: Laporan)

    @Delete
    fun delete(laporan: Laporan)

    @Query("SELECT * FROM Laporan where laporan.pemasukan is not null ORDER BY laporan.tanggal DESC  ")
    fun getPemasukan(): LiveData<List<Laporan>>

    @Query("SELECT * FROM Laporan where Laporan.pengeluaran is not null ORDER BY Laporan.tanggal DESC  ")
    fun getPengeluaran(): LiveData<List<Laporan>>

    @Query("SELECT * FROM Laporan WHERE Laporan.tanggal = :date AND Laporan.pemasukan is not null")
    fun getPemasukanByDate(date: String): LiveData<List<Laporan>>

    @Query("SELECT * FROM Laporan WHERE Laporan.tanggal = :date AND Laporan.pengeluaran is not null")
    fun getPengeluaranByDate(date: String): LiveData<List<Laporan>>

    @Query("SELECT id, strftime(\"%m / %Y\",tanggal) as tanggal,sum(pemasukan) as pemasukan, sum(pengeluaran) as pengeluaran,sum(total) as total from laporan  group by strftime(\"%m / %Y\",tanggal)")
    fun getLaporan():LiveData<List<Laporan>>

    @Query("SELECT id, sum(total) as total from laporan")
    fun getTotal(): LiveData<List<Laporan>>

}