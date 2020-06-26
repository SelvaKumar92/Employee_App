package com.selva.example_employee_app.di.fragmentModule

import androidx.lifecycle.ViewModel
import com.selva.example_employee_app.di.anotation.ViewModelKey
import com.selva.example_employee_app.viewmodel.AttendanceViewModel
import com.selva.example_employee_app.viewmodel.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AttendanceViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AttendanceViewModel::class)
    fun bindDetailViewModel(userViewModel: AttendanceViewModel): ViewModel
}