package com.selva.example_employee_app.preference


interface SharedPreference {

    fun isInitDataLoaded() : Boolean

    fun initDataLoaded(value:Boolean)
}