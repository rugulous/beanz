package com.rugulous.beanz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.rugulous.beanz.domain.AccountBuilder
import com.rugulous.beanz.domain.AccountType
import com.rugulous.beanz.extensions.toMillis
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AddAccountActivity : AppCompatActivity() {
    private val accountBuilder = AccountBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        initEventHandlers()
    }

    private fun initEventHandlers(){
        val maturityDate : TextView = findViewById(R.id.new_account_maturity)
        val accountType : RadioGroup = findViewById(R.id.account_type)
        val now = LocalDate.now()

        accountType.setOnCheckedChangeListener(::onAccountTypeChanged)

        val dpd = DatePickerDialog(this, ::onDateChosen, now.year, now.monthValue, now.dayOfMonth)
        dpd.datePicker.minDate = now.toMillis()
        maturityDate.setOnClickListener{
            dpd.show()
        }
    }

    private fun onAccountTypeChanged(group: RadioGroup, checkedId: Int){
        val type = AccountType.values()[checkedId - 1]
        accountBuilder.accountType(type)

        val step2 : CardView = findViewById(R.id.add_step_2)
        if(step2.visibility == View.VISIBLE){
            return
        }

        Utils.fadeIn(step2)
    }

    private fun onDateChosen(v : View, year : Int, monthOfYear : Int, dayOfMonth: Int){
        if(v !is TextView){
            return
        }

        val date: LocalDate = LocalDate.of(year, monthOfYear, dayOfMonth)

        accountBuilder.maturityDate(date)
        v.text = "Maturity date: ${date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}"
    }
}