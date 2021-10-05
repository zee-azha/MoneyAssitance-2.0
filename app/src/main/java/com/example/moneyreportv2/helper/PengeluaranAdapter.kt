package com.example.moneyreportv2.helper

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.databinding.ItemListBinding
import com.example.moneyreportv2.ui.pemasukan.PemasukanAddUpdate
import com.example.moneyreportv2.ui.pengeluaran.PengeluaranAddUpdate


class PengeluaranAdapter: RecyclerView.Adapter<PengeluaranAdapter.LaporanViewHolder>() {
    private val listLaporan = ArrayList<Laporan>()

    fun setListPengeluaran(listLaporan: List<Laporan>) {
        val diffCallback = LaporanDiffCallback(this.listLaporan, listLaporan)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listLaporan.clear()
        this.listLaporan.addAll(listLaporan)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaporanViewHolder(binding)
    }
    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        holder.bind(listLaporan[position])
    }
    override fun getItemCount(): Int {
        return listLaporan.size
    }
    inner class LaporanViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(laporan: Laporan) {
            with(binding) {
                tvItemDate.text = laporan.date.toString()
                tvItemDescription.text = laporan.description
                tvItemCategory.text = laporan.category
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, PengeluaranAddUpdate::class.java)
                    intent.putExtra(PengeluaranAddUpdate.EXTRA_Pengeluaran, laporan)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}