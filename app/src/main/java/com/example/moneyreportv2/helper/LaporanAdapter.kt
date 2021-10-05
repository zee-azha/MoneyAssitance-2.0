package com.example.moneyreportv2.helper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.databinding.ItemListBinding
import com.example.moneyreportv2.databinding.ReportListBinding
import com.example.moneyreportv2.ui.pemasukan.PemasukanAddUpdate
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class LaporanAdapter: RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {
    private val listLaporan = ArrayList<Laporan>()

    fun setListLaporan(listLaporan: List<Laporan>) {
        val diffCallback = LaporanDiffCallback(this.listLaporan, listLaporan)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listLaporan.clear()
        this.listLaporan.addAll(listLaporan)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val binding = ReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaporanViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        holder.bind(listLaporan[position])
    }
    override fun getItemCount(): Int {
        return listLaporan.size
    }
    inner class LaporanViewHolder(private val binding: ReportListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(laporan: Laporan) {
            with(binding) {
                tvItemDate.text = laporan.date
                tvItemPemasukan.text = laporan.pemasukan.toString()
                tvItemPengeluaran.text = laporan.pengeluaran.toString()
                tvItemTotal.text =laporan.amount.toString()


            }
        }
    }
}