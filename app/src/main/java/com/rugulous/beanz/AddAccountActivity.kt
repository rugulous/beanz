package com.rugulous.beanz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddAccountActivity : AppCompatActivity() {
    private val calendar : Calendar = Calendar.getInstance()
    private lateinit var maturityDate : TextView
    private lateinit var dpd : DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        dpd = DatePickerDialog(this, ::onDateChosen, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        dpd.datePicker.minDate = calendar.timeInMillis

        maturityDate = findViewById(R.id.new_account_maturity)
        maturityDate.setOnClickListener{
            dpd.show()
        }
    }

    private fun onDateChosen(v : View, year : Int, monthOfYear : Int, dayOfMonth: Int){
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        maturityDate.text = "Maturity date: " + dateFormat.format(calendar.time)
    }
}