package com.rugulous.beanz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import android.widget.RadioButton
import androidx.cardview.widget.CardView
import com.rugulous.beanz.domain.AccountBuilder
import com.rugulous.beanz.domain.AccountType


class AddAccountActivity : AppCompatActivity() {
    private val calendar : Calendar = Calendar.getInstance()
    private val accountBuilder = AccountBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        initEventHandlers()
    }

    private fun initEventHandlers(){
        val maturityDate : TextView = findViewById(R.id.new_account_maturity)
        val accountType : RadioGroup = findViewById(R.id.account_type)
        val step2 : CardView = findViewById(R.id.add_step_2)

        accountType.setOnCheckedChangeListener { group, checkedId ->
            val type = AccountType.values()[checkedId]
            accountBuilder.Type(type)

            if(step2.visibility == View.VISIBLE){
                return@setOnCheckedChangeListener
            }

            Utils.fadeIn(step2)
        }

        val dpd = DatePickerDialog(this, ::onDateChosen, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        dpd.datePicker.minDate = calendar.timeInMillis
        maturityDate.setOnClickListener{
            dpd.show()
        }
    }
    
    private fun onDateChosen(v : View, year : Int, monthOfYear : Int, dayOfMonth: Int){
        if(v !is TextView){
            return
        }

        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, monthOfYear)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        v.text = "Maturity date: ${dateFormat.format(calendar.time)}"
    }
}