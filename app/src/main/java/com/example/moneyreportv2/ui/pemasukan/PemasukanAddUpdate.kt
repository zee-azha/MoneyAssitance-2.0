package com.example.moneyreportv2.ui.pemasukan

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
import com.example.moneyreportv2.databinding.ActivityPemasukanAddUpdateBinding
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanAddUpdateViewModel
import com.example.moneyreportv2.viewmodel.pemasukan.PemasukanViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

class PemasukanAddUpdate : AppCompatActivity(){

    companion object{
        const val EXTRA_PEMASUKAN = "extra_pemasukan"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private lateinit var pemasukanAddUpdateViewModel: PemasukanAddUpdateViewModel
    private var isEdit = false
    private var pemasukan: Laporan? = null

    private var _activityPemasukanAddUpdateBinding: ActivityPemasukanAddUpdateBinding? = null
    private val binding get() = _activityPemasukanAddUpdateBinding
    private var dateFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityPemasukanAddUpdateBinding =
            ActivityPemasukanAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ArrayAdapter.createFromResource(
            this,
            R.array.kategori_pemasukan,
            android.R.layout.simple_spinner_dropdown_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding?.category?.adapter  = adapter
        }

        pemasukanAddUpdateViewModel = obtainViewModel(this@PemasukanAddUpdate)

        pemasukan = intent.getParcelableExtra(EXTRA_PEMASUKAN)
        if (pemasukan != null) {
            isEdit = true
        } else {
            pemasukan = Laporan()

        }
        val actionBarTitle: String
        val btnTitle: String
        if (isEdit) {
            actionBarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (pemasukan != null) {

                pemasukan?.let { pemasukan ->
                    binding?.edtDate?.setText(pemasukan.date)
                    if(pemasukan.category.equals("Utama")){
                        binding?.category?.setSelection(0)

                    }else{
                        binding?.category?.setSelection(1)
                    }
                    binding?.edtAmount?.setText(pemasukan.amount.toString())
                    binding?.edtDescription?.setText(pemasukan.description)
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
        val income = binding?.edtAmount?.text.toString().trim()
        val deskripsi = binding?.edtDescription?.text.toString().trim()

        when{
            date.isEmpty()->{
                binding?.edtDate?.error = getString(R.string.empty)
            } spinner.isEmpty()->{
                Toast.makeText(this,R.string.empty,Toast.LENGTH_LONG).show()
            } income.isEmpty()->{
                binding?.edtAmount?.error = getString(R.string.empty)
            } deskripsi.isEmpty()->{
                binding?.edtDescription?.error = getString(R.string.empty)
            }else ->{

                pemasukan?.let { pemasukan ->
                    pemasukan?.date = date
                    pemasukan?.category = spinner
                    pemasukan?.pemasukan = income.toInt()
                    pemasukan?.pengeluaran = null
                    pemasukan?.description = deskripsi
                    pemasukan?.amount = 0 + income.toInt()

                }

                    if (isEdit){
                        pemasukanAddUpdateViewModel.update(pemasukan as Laporan)
                        showToast(getString(R.string.update))

                    }else{
                        pemasukanAddUpdateViewModel.insert(pemasukan as Laporan)
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
        _activityPemasukanAddUpdateBinding = null
    }

    private fun obtainViewModel(activity: AppCompatActivity): PemasukanAddUpdateViewModel {
        val factory = PemasukanViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity,factory).get(PemasukanAddUpdateViewModel::class.java)

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
                    pemasukanAddUpdateViewModel.delete(pemasukan as Laporan)
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


