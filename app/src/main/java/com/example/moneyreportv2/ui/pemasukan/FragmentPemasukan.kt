package com.example.moneyreportv2.ui.pemasukan


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moneyreportv2.R
import com.example.moneyreportv2.databinding.FragmentPemasukanBinding
import com.example.moneyreportv2.helper.pemasukan.PemasukanAdapter
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanMainViewModel
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class FragmentPemasukan : Fragment(R.layout.fragment_pemasukan){

    private var _activityMainBinding : FragmentPemasukanBinding? = null
    private val binding get() = _activityMainBinding
    private lateinit var adapter: PemasukanAdapter
    private var dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _activityMainBinding= FragmentPemasukanBinding.bind(view)

        adapter = PemasukanAdapter()
        binding?.rvPemasukan?.layoutManager = LinearLayoutManager(activity)
        binding?.rvPemasukan?.setHasFixedSize(true)
        binding?.rvPemasukan?.adapter = adapter

        binding?.fabAdd?.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(activity, PemasukanAddUpdate::class.java)
                startActivity(intent)
            }
        }
        binding?.edtDate?.setOnClickListener{
            showDialog()

        }
        binding?.btnSearch?.setOnClickListener{
            val date = binding?.edtDate?.text.toString()
            if (date.isNotEmpty()) {
                val mainViewModel = obtainViewModel(activity as AppCompatActivity)
                mainViewModel.getPemasukanByDate(date)
                    .observe(viewLifecycleOwner, { pemasukanList ->
                        if (pemasukanList != null) {
                            adapter.setListPemasukan(pemasukanList)
                        }
                    })
            }else{
                val mainViewModel = obtainViewModel(activity as AppCompatActivity)
                mainViewModel.getPemasukan().observe(viewLifecycleOwner, { pemasukanList ->
                    if (pemasukanList != null) {
                        adapter.setListPemasukan(pemasukanList)
                    }
                })
            }
        }

            val mainViewModel = obtainViewModel(activity as AppCompatActivity)
            mainViewModel.getPemasukan().observe(viewLifecycleOwner, { pemasukanList ->
                if (pemasukanList != null) {
                    adapter.setListPemasukan(pemasukanList)
                }
            })





    }

    private fun obtainViewModel(appCompatActivity: AppCompatActivity): PemasukanMainViewModel {
        val factory = PemasukanViewModelFactory.getInstance(appCompatActivity.application)
        return ViewModelProvider(appCompatActivity,factory).get(PemasukanMainViewModel::class.java)


    }
    private fun showDialog() {
        val newCalendar = Calendar.getInstance()
        val datePickerDialog = activity?.let {
            DatePickerDialog(
                it,
                { view, year, monthOfYear, dayOfMonth ->
                    val newDate = Calendar.getInstance()
                    newDate[year, monthOfYear] = dayOfMonth
                    binding?.edtDate?.setText(dateFormatter.format(newDate.time))
                }, newCalendar[Calendar.YEAR], newCalendar[Calendar.MONTH],
                newCalendar[Calendar.DAY_OF_MONTH]
            )
        }
        datePickerDialog?.show()
    }




}