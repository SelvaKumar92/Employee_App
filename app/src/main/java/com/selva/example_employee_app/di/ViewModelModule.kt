package com.selva.example_employee_app.di

import androidx.lifecycle.ViewModelProvider
import com.selva.example_employee_app.viewmodel.*


import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
