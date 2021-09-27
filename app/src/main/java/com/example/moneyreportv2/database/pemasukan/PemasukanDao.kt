package com.example.moneyreportv2.database.pemasukan

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface PemasukanDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(pemasukan: Pemasukan)

    @Update
    fun update(pemasukan: Pemasukan)

    @Delete
    fun delete(pemasukan: Pemasukan)

    @Query("SELECT * FROM pemasukan ORDER BY pemasukan.tanggal DESC")
    fun getPemasukan(): LiveData<List<Pemasukan>>

    @Query("SELECT * FROM pemasukan WHERE pemasukan.tanggal = :date")
    fun getPemasukanByDate(date: String): LiveData<List<Pemasukan>>

//    @Query("SELECT * FROM pemasukan WHERE pemasukan.kategori = :category")
//    fun getPemasukanByCategory(category: String): LiveData<List<Pemasukan>>
//
//    @Query("SELECT * FROM pemasukan WHERE pemasukan.kategori= :category AND pemasukan.tanggal =:date")
//    fun getPemasukanByCategoryAndDate(category: String, date: String): LiveData<List<Pemasukan>>

}