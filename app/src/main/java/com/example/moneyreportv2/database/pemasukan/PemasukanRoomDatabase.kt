package com.example.moneyreportv2.database.pemasukan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moneyreportv2.util.DateConverter

@Database(entities = [Pemasukan::class], version = 1)
//@TypeConverters(DateConverter::class)
abstract class PemasukanRoomDatabase: RoomDatabase() {

    abstract fun pemasukanDao(): PemasukanDao

    companion object{
        @Volatile
        private var INSTANCE: PemasukanRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): PemasukanRoomDatabase {
            if (INSTANCE == null){
                synchronized(PemasukanRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    PemasukanRoomDatabase::class.java,"pemasukan_database")
                        .build()
                }
            }
            return INSTANCE as PemasukanRoomDatabase
        }
    }

}