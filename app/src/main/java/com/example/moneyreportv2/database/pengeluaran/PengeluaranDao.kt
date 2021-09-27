package com.example.moneyreportv2.database.pengeluaran

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface PengeluaranDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPengeluaran(pengeluaran: Pengeluaran)

    @Update
    fun update(pengeluaran: Pengeluaran)

    @Delete
    fun delete(pengeluaran: Pengeluaran)

    @Query("SELECT * FROM pengeluaran ORDER BY pengeluaran.tanggal DESC")
    fun getPengeluaran(): LiveData<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran WHERE pengeluaran.tanggal = :date")
    fun getPengeluaranByDate(date: String): LiveData<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran WHERE pengeluaran.kategori = :category")
    fun getPengeluaranByCategory(category: String): LiveData<List<Pengeluaran>>

    @Query("SELECT * FROM pengeluaran WHERE pengeluaran.kategori= :category AND pengeluaran.tanggal =:date")
    fun getPengeluaranByCategoryAndDate(category: String, date: String): LiveData<List<Pengeluaran>>
}