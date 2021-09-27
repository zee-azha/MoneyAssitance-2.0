package com.example.moneyreportv2.helper.pemasukan

import androidx.recyclerview.widget.DiffUtil
import com.example.moneyreportv2.database.pemasukan.Pemasukan

class PemasukanDiffCallback(private val mOldPemasukan: List<Pemasukan>, private val mNewPemasukan: List<Pemasukan>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldPemasukan.size
    }

    override fun getNewListSize(): Int {
        return mNewPemasukan.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldPemasukan[oldItemPosition].id == mNewPemasukan[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldEmployee = mOldPemasukan[oldItemPosition]
        val newEmployee = mNewPemasukan[newItemPosition]

        return oldEmployee.date == newEmployee.date && oldEmployee.category == newEmployee.category && oldEmployee.amount == newEmployee.amount && oldEmployee.description == newEmployee.description
    }


}