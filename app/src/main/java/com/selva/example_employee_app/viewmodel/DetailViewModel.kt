package com.selva.example_employee_app.viewmodel

import androidx.lifecycle.ViewModel
import com.selva.example_employee_app.repository.EmployeeRepository
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val repository: EmployeeRepository) :
    ViewModel() {

    var uid = -1

    fun getEmployee() = repository.getEmployee(uid)

}