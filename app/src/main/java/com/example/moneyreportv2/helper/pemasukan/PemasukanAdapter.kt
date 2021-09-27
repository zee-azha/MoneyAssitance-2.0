package com.example.moneyreportv2.helper.pemasukan

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moneyreportv2.database.pemasukan.Pemasukan
import com.example.moneyreportv2.databinding.ItemListBinding
import com.example.moneyreportv2.ui.pemasukan.PemasukanAddUpdate

class PemasukanAdapter: RecyclerView.Adapter<PemasukanAdapter.PemasukanViewHolder>() {
    private val listPemasukan = ArrayList<Pemasukan>()
    fun setListPemasukan(listPemasukan: List<Pemasukan>) {
        val diffCallback = PemasukanDiffCallback(this.listPemasukan, listPemasukan)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listPemasukan.clear()
        this.listPemasukan.addAll(listPemasukan)
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemasukanViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PemasukanViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PemasukanViewHolder, position: Int) {
        holder.bind(listPemasukan[position])
    }
    override fun getItemCount(): Int {
        return listPemasukan.size
    }
    inner class PemasukanViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pemasukan: Pemasukan) {
            with(binding) {
                tvItemDate.text = pemasukan.date.toString()
                tvItemDescription.text = pemasukan.description
                tvItemCategory.text = pemasukan.category
                cvItemNote.setOnClickListener {
                    val intent = Intent(it.context, PemasukanAddUpdate::class.java)
                    intent.putExtra(PemasukanAddUpdate.EXTRA_PEMASUKAN, pemasukan)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}