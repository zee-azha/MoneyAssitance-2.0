package com.example.moneyreportv2.ui.pengeluaran

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.moneyreportv2.R
import com.example.moneyreportv2.database.Laporan
import com.example.moneyreportv2.databinding.ActivityPengeluaranAddUpdateBinding
import com.example.moneyreportv2.viewmodel.pengeluaran.PengeluaranAddUpdateViewModel
import com.example.moneyreportv2.viewmodel.pengeluaran.PengeluaranViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class PengeluaranAddUpdate : AppCompatActivity(){

    companion object{
        const val EXTRA_Pengeluaran = "extra_Pengeluaran"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private lateinit var pengeluaranAddUpdateViewModel: PengeluaranAddUpdateViewModel

    private var isEdit = false
    private var pengeluaran: Laporan? = null

    private var _activityPengeluaranAddUpdateBinding: ActivityPengeluaranAddUpdateBinding? = null
    private val binding get() = _activityPengeluaranAddUpdateBinding
    private var dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityPengeluaranAddUpdateBinding =
            ActivityPengeluaranAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.kategori_pengeluaran,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.category?.adapter  = adapter
        }

        pengeluaranAddUpdateViewModel = obtainViewModel(this@PengeluaranAddUpdate)

        pengeluaran = intent.getParcelableExtra(EXTRA_Pengeluaran)
        if (pengeluaran != null) {
            isEdit = true
        } else {
            pengeluaran = Laporan()
        }
        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (pengeluaran != null) {
                pengeluaran?.let { pengeluaran ->
                    binding?.edtDate?.setText(pengeluaran.date)
                    if(pengeluaran.category.equals("Makan")){
                        binding?.category?.setSelection(0)
                    }else if(pengeluaran.category.equals("Hiburan")){
                        binding?.category?.setSelection(1)
                    }else if(pengeluaran.category.equals("Pembayaran")){
                        binding?.category?.setSelection(2)
                    }else{
                        binding?.category?.setSelection(3)
                    }

                    binding?.edtAmount?.setText(pengeluaran.amount.toString())
                    binding?.edtDescription?.setText(pengeluaran.description)
                }
            }
        } else {
            actionBarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.btnSubmit?.text = btnTitle

        binding?.edtDate?.setOnClickListener{
            showDialog()
        }

        binding?.btnSubmit?.setOnClickListener{
            submitData()
        }


    }


    private fun submitData() {
        val date = binding?.edtDate?.text.toString().trim()
        val spinner = binding?.category?.selectedItem.toString().trim()
        val expense = binding?.edtAmount?.text.toString().trim()
        val deskripsi = binding?.edtDescription?.text.toString().trim()
        when{
            date.isEmpty()->{
                binding?.edtDate?.error = getString(R.string.empty)
            } spinner.isEmpty()->{
                Toast.makeText(this,R.string.empty,Toast.LENGTH_LONG).show()
            } expense.isEmpty()->{
                binding?.edtAmount?.error = getString(R.string.empty)
            } deskripsi.isEmpty()->{
                binding?.edtDescription?.error = getString(R.string.empty)
            }else ->{
                pengeluaran?.let { pengeluaran ->
                    pengeluaran?.date = date
                    pengeluaran?.category = spinner
                    pengeluaran?.pengeluaran = expense.toInt()
                    pengeluaran?.pemasukan = null
                    pengeluaran?.description = deskripsi
                    pengeluaran?.amount = 0 - expense.toInt()

                }
                    if (isEdit){
                        pengeluaranAddUpdateViewModel.update(pengeluaran as Laporan)
                        showToast(getString(R.string.update))

                    }else{
                        pengeluaranAddUpdateViewModel.insert(pengeluaran as Laporan)
                        showToast(getString(R.string.added))
                    }
                    finish()

            }

        }
    }

    private fun showToast(string: String) {
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show()

    }


    private fun showDialog() {
        val newCalendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val newDate = Calendar.getInstance()
                newDate[year, monthOfYear] = dayOfMonth
                binding?.edtDate?.setText(dateFormatter.format(newDate.time))
            }, newCalendar[Calendar.YEAR], newCalendar[Calendar.MONTH],
            newCalendar[Calendar.DAY_OF_MONTH]
        )
        datePickerDialog.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityPengeluaranAddUpdateBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): PengeluaranAddUpdateViewModel {
        val factory = PengeluaranViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity,factory).get(PengeluaranAddUpdateViewModel::class.java)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(alertDialogDelete: Int) {
        val isDialogClose = alertDialogDelete == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.message_cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.yes)) { _, _ ->
                if (!isDialogClose) {
                    pengeluaranAddUpdateViewModel.delete(pengeluaran as Laporan)
                    showToast(getString(R.string.deleted))
                }
                finish()
            }
            setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.cancel() }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}


