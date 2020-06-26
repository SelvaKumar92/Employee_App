package com.selva.example_employee_app.di.activityModule

import androidx.lifecycle.ViewModel
import com.selva.example_employee_app.di.anotation.ViewModelKey
import com.selva.example_employee_app.viewmodel.LauncherViewModel
import com.selva.example_employee_app.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLauncherViewModel(userViewModel: LoginViewModel): ViewModel
}