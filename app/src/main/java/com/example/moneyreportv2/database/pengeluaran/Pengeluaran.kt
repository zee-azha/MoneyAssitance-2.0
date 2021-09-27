package com.example.moneyreportv2.database.pengeluaran

import android.os.Parcelable
import androidx.room.*
import com.example.moneyreportv2.util.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
class Pengeluaran (
@PrimaryKey(autoGenerate = true)
@ColumnInfo(name = "id")
var id: Int = 0,

@ColumnInfo(name = "tanggal")
var date: String? = null,

@ColumnInfo(name = "kategori")
var category: String? = null,

@ColumnInfo(name = "pengeluaran")
var amount: Int? = null,

@ColumnInfo(name = "deskripsi")
var description: String? = null

):Parcelable