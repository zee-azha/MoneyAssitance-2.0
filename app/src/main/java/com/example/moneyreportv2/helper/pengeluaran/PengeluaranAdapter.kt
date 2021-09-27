package com.example.moneyreportv2.helper.pengeluaran

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyreportv2.database.pengeluaran.Pengeluaran
import com.example.moneyreportv2.databinding.ItemListBinding
import com.example.moneyreportv2.ui.pengeluaran.PengeluaranAddUpdate


class PengeluaranAdapter: RecyclerView.Adapter<PengeluaranAdapter.PengeluaranViewHolder>() {
    private val listPengeluaran = ArrayList<Pengeluaran>()
    fun setListPengeluaran(listPengeluaran: List<Pengeluaran>) {
        val diffCallback = PengeluaranDiffCallback(this.listPengeluaran, listPengeluaran)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPengeluaran.clear()
        this.listPengeluaran.addAll(listPengeluaran)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengeluaranViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PengeluaranViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PengeluaranViewHolder, position: Int) {
        holder.bind(listPengeluaran[position])
    }
    override fun getItemCount(): Int {
        return listPengeluaran.size
    }
    inner class PengeluaranViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(Pengeluaran: Pengeluaran) {
            with(binding) {
                tvItemDate.text = Pengeluaran.date.toString()
                tvItemDescription.text = Pengeluaran.description
                tvItemCategory.text = Pengeluaran.category
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, PengeluaranAddUpdate::class.java)
                    intent.putExtra(PengeluaranAddUpdate.EXTRA_Pengeluaran, Pengeluaran)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}