package com.rugulous.beanz.domain

import android.app.admin.SystemUpdatePolicy.ValidationFailedException
import java.time.LocalDate

class AccountBuilder {
    private var accountType: AccountType? = null
    private var accountAccessType: AccountAccessType? = null
    private var maturityDate : LocalDate? = null

    fun accountType(type: AccountType): AccountBuilder{
        this.accountType = type
        return this
    }

    fun accessType(type: AccountAccessType): AccountBuilder{
        this.accountAccessType = type
        return this
    }

    fun maturityDate(date: LocalDate): AccountBuilder{
        this.maturityDate = date
        return this
    }

    fun canBuild(): Boolean{
        if(accountType == null || accountAccessType == null){
            return false
        }

        return true
    }

    fun build(): IAccount{
        if(!canBuild()){
            throw Exception()
        }

        if(accountAccessType == AccountAccessType.STANDARD){
            return Account("", 1f)
        }

        if(accountAccessType == AccountAccessType.NOTICE){
            return NoticeAccount("", 1f, TermType.DAY, 1)
        }

        return FixedTermAccount("", 1f, LocalDate.now())
    }
}