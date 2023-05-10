package com.rugulous.beanz.domain

import java.time.LocalDate

interface IAccount{
    val name: String
    val interestRate: Float

    fun getAccessDate(): LocalDate
}

class Account(override val name : String, override val interestRate : Float) : IAccount{
    override fun getAccessDate(): LocalDate {
        return LocalDate.now()
    }
}

class NoticeAccount(override val name: String, override val interestRate: Float, val termType : TermType, val termDuration : Long) : IAccount{
    override fun getAccessDate(): LocalDate {
        val date: LocalDate = LocalDate.now()

        if(termType == TermType.DAY){
            return date.plusDays(termDuration)
        }

        if(termType == TermType.MONTH){
            return date.plusMonths(termDuration)
        }

        return date.plusYears(termDuration)
    }
}