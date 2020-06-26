package com.selva.example_employee_app.di.activityModule

import androidx.lifecycle.ViewModel
import com.selva.example_employee_app.di.anotation.ViewModelKey
import com.selva.example_employee_app.viewmodel.EmployeeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EmployeeModule {
    @Binds
    @IntoMap
    @ViewModelKey(EmployeeViewModel::class)
    fun bindLauncherViewModel(userViewModel: EmployeeViewModel): ViewModel
}