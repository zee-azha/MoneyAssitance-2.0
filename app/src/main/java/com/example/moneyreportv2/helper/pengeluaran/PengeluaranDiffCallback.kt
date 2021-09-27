package com.example.moneyreportv2.helper.pengeluaran

import androidx.recyclerview.widget.DiffUtil
import com.example.moneyreportv2.database.pengeluaran.Pengeluaran


class PengeluaranDiffCallback(private val mOldPengeluaran: List<Pengeluaran>, private val mNewPengeluaran: List<Pengeluaran>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPengeluaran.size
    }

    override fun getNewListSize(): Int {
        return mNewPengeluaran.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPengeluaran[oldItemPosition].id == mNewPengeluaran[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldPengeluaran[oldItemPosition]
        val newEmployee = mNewPengeluaran[newItemPosition]

        return oldEmployee.date == newEmployee.date && oldEmployee.category == newEmployee.category && oldEmployee.amount == newEmployee.amount && oldEmployee.description == newEmployee.description
    }


}