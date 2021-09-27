package com.example.moneyreportv2.util

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.moneyreportv2.ui.pemasukan.FragmentPemasukan
import com.example.moneyreportv2.ui.pengeluaran.FragmentPengeluaran

class PagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {



    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FragmentPemasukan()
            1 -> fragment = FragmentPengeluaran()
        }
        return fragment as Fragment
    }
}