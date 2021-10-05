package com.example.moneyreportv2.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.moneyreportv2.database.Laporan


class LaporanDiffCallback(private val mOldLaporan: List<Laporan>, private val mNewLaporan: List<Laporan>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldLaporan.size
    }

    override fun getNewListSize(): Int {
        return mNewLaporan.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldLaporan[oldItemPosition].id == mNewLaporan[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldLaporan[oldItemPosition]
        val newEmployee = mNewLaporan[newItemPosition]

        return oldEmployee.date == newEmployee.date && oldEmployee.category == newEmployee.category && oldEmployee.amount == newEmployee.amount && oldEmployee.description == newEmployee.description
    }


}