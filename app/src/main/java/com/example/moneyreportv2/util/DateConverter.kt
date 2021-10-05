package com.example.moneyreportv2.util

import android.provider.SyncStateContract
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

object DateConverter {
    @TypeConverter
    fun toString(sd: String): Long? {
        val sdf = SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH)
        return sdf.parse(sd).time
    }

    @TypeConverter
    fun toLong(longDate: Long): String? {
        val sdf = SimpleDateFormat("yyyy/mm/dd", Locale.ENGLISH)
        return sdf.format(Date(longDate))
    }
}