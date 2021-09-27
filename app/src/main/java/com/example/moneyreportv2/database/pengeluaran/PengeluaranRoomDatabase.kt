package com.example.moneyreportv2.database.pengeluaran

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneyreportv2.util.DateConverter

@Database(entities = [Pengeluaran::class],version = 1)
abstract class PengeluaranRoomDatabase:RoomDatabase() {

    abstract fun PengeluaranDao(): PengeluaranDao

    companion object{
        @Volatile
        private var INSTANCE: PengeluaranRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PengeluaranRoomDatabase {
            if (INSTANCE == null){
                synchronized(PengeluaranRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PengeluaranRoomDatabase::class.java,"Pengeluaran_database")
                        .build()
                }
            }
            return INSTANCE as PengeluaranRoomDatabase
        }
    }
}