package com.rugulous.beanz

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.rugulous.beanz.domain.AccountAccessType
import com.rugulous.beanz.domain.AccountBuilder
import com.rugulous.beanz.domain.AccountType
import com.rugulous.beanz.extensions.toMillis
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.reflect.typeOf


class AddAccountActivity : AppCompatActivity() {
    private val accountBuilder = AccountBuilder()
    private lateinit var maturityDate: TextView
    private lateinit var noticePeriod: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account)

        maturityDate = findViewById(R.id.new_account_maturity)
        noticePeriod = findViewById(R.id.new_account_notice)

        initEventHandlers()
    }

    private fun initEventHandlers(){
        val accountType : RadioGroup = findViewById(R.id.account_type)
        val name : TextInputEditText = findViewById((R.id.new_account_name))
        val interest : TextInputEditText = findViewById(R.id.new_account_interest)
        val accessType : RadioGroup = findViewById(R.id.new_account_access)
        val now = LocalDate.now()

        accountType.setOnCheckedChangeListener(::onAccountTypeChanged)

        name.doAfterTextChanged {
            accountBuilder.name(it.toString())
            showSaveIfReady()
        }

        interest.doAfterTextChanged {
            val value = it.toString()
            val parsed = value.toFloatOrNull()
            accountBuilder.interestRate(parsed)
            showSaveIfReady()
        }

        accessType.setOnCheckedChangeListener(::onAccessTypeChanged)

        val dpd = DatePickerDialog(this, ::onDateChosen, now.year, now.monthValue, now.dayOfMonth)
        dpd.datePicker.minDate = now.toMillis()
        maturityDate.setOnClickListener{
            dpd.show()
        }
    }

    private fun onAccountTypeChanged(group: RadioGroup, checkedId: Int){
        val pos: Int = group.indexOfChild(findViewById(checkedId))
        val type = AccountType.values()[pos]
        accountBuilder.accountType(type)

        val step2 : CardView = findViewById(R.id.add_step_2)
        if(step2.visibility == View.VISIBLE){
            return
        }

        Utils.fadeIn(step2)
    }

    private fun onAccessTypeChanged(group: RadioGroup, checkedId: Int){
        val pos: Int = group.indexOfChild(findViewById(checkedId))
        val type = AccountAccessType.values()[pos]
        accountBuilder.accessType(type)

        if(type == AccountAccessType.NOTICE){
            Utils.crossFade(maturityDate, noticePeriod)
        } else if(type == AccountAccessType.FIXED_TERM){
            Utils.crossFade(noticePeriod, maturityDate)
        }

        showSaveIfReady()
    }

    private fun onDateChosen(v : View, year : Int, monthOfYear : Int, dayOfMonth: Int){
        val date: LocalDate = LocalDate.of(year, monthOfYear, dayOfMonth)

        accountBuilder.maturityDate(date)
        maturityDate.text = "Maturity date: ${date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}"
    }

    private fun showSaveIfReady(){
        val save: Button = findViewById(R.id.create_account)

        if(accountBuilder.canBuild()){
            Utils.fadeIn(save)
        } else {
            Utils.fadeOut(save)
        }
    }
}