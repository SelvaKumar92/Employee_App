package com.selva.example_employee_app.di

import com.selva.example_employee_app.di.activityModule.EmployeeModule
import com.selva.example_employee_app.di.activityModule.LauncherModule
import com.selva.example_employee_app.di.activityModule.LoginModule
import com.selva.example_employee_app.di.activityModule.TabPageModule
import com.selva.example_employee_app.di.anotation.ActivityScoped
import com.selva.example_employee_app.ui.activity.EmployeeActivity
import com.selva.example_employee_app.ui.activity.LauncherActivity
import com.selva.example_employee_app.ui.activity.LoginActivity
import com.selva.example_employee_app.ui.activity.TabPageActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("UNUSED")
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    fun launcherActivity(): LauncherActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TabPageModule::class])
    fun tabPageActivity(): TabPageActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [EmployeeModule::class])
    fun employeeActivity(): EmployeeActivity
}