package com.example.moneyreportv2.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moneyreportv2.util.DateConverter
import kotlinx.parcelize.Parcelize
import java.util.*

@Entity
@Parcelize
class Laporan (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "tanggal")
    var date: String? = null,

    @ColumnInfo(name = "kategori")
    var category: String? = null,

    @ColumnInfo(name = "deskripsi")
    var description: String? = null,

    @ColumnInfo(name = "pemasukan", )
    var pemasukan: Int? = null,

    @ColumnInfo(name = "pengeluaran")
    var pengeluaran: Int? = null,

    @ColumnInfo(name = "total")
    var amount: Int? = null


):Parcelable