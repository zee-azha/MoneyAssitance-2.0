package com.example.moneyreportv2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneyreportv2.util.DateConverter

@Database(entities = [Laporan::class], version = 1,exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class LaporanRoomDatabase: RoomDatabase() {

    abstract fun pemasukanDao(): LaporanDao


    companion object{
        @Volatile
        private var INSTANCE: LaporanRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): LaporanRoomDatabase {
            if (INSTANCE == null){
                synchronized(LaporanRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LaporanRoomDatabase::class.java,"Laporan_database")
                        .build()
                }
            }
            return INSTANCE as LaporanRoomDatabase
        }
    }

}