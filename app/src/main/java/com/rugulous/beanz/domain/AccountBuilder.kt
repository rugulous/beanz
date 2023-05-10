package com.rugulous.beanz.domain

class AccountBuilder {
    private var accountType: AccountType? = null

    fun Type(type: AccountType): AccountBuilder{
        this.accountType = type
        return this
    }

    fun CanBuild(): Boolean{
        return false
    }

    fun Build(): IAccount{
        return Account("Test", 1f)
    }
}