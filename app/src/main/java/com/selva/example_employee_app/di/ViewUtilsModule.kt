package com.selva.example_employee_app.di

import com.selva.example_employee_app.util.ViewUtils
import com.selva.example_employee_app.util.ViewUtilsImpl
import dagger.Binds
import dagger.Module

@Module
interface ViewUtilsModule {
    @Binds
    fun bindViewUtil(util:ViewUtilsImpl):ViewUtils
}