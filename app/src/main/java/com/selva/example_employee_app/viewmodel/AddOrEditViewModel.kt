package com.selva.example_employee_app.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.selva.example_employee_app.R
import com.selva.example_employee_app.model.Employee
import com.selva.example_employee_app.repository.EmployeeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddOrEditViewModel @Inject constructor(
    application: Application,
    private val repository: EmployeeRepository
) : AndroidViewModel(application) {


    // if uid < 0, then add, if uid>=0, then edit employee
    var uid = -1
    var rating = 0.0f

    private val _onBackPressMLD = MutableLiveData<Boolean>()
    val onBackPressLD: LiveData<Boolean> = _onBackPressMLD

    fun getEmployee(uid: Int) = repository.getEmployee(uid)

    fun doneButtonPressed(
        name: String,
        id: String,
        age: String,
        address: String,
        phone: String,
        email: String,
        password: String,
        desgination: String,
        rManager: String,
        hr: String
    ) {
        if (validateInput(
                name,
                id,
                age,
                address,
                phone,
                email,
                password,
                desgination,
                rManager,
                hr
            )
        )
            addOrEditEmployee(
                name,
                id,
                age,
                address,
                phone,
                email,
                password,
                desgination,
                rManager,
                hr
            )
        else
            Toast.makeText(getApplication(), R.string.invalid_info, Toast.LENGTH_SHORT).show()
    }

    private fun validateInput(
        name: String,
        id: String,
        age: String,
        address: String,
        phone: String,
        email: String,
        password: String,
        desgination: String,
        rManager: String,
        hr: String
    ): Boolean {
        if (name.trim() == "" || id.trim() == "" || age.trim() == "" || address.trim() == "" || phone.trim() == "" || email.trim() == "" || password.trim() == "" || desgination.trim() == "" || rManager.trim() == "" || hr.trim() == "")
            return false
        return true
    }


    private fun addOrEditEmployee(
        name: String,
        id: String,
        age: String,
        address: String,
        phone: String,
        email: String,
        password: String,
        desgination: String,
        rManager: String,
        hr: String
    ) {
        if (uid < 0)
            addEmployee(
                name,
                id,
                age,
                address,
                phone,
                email,
                password,
                desgination,
                rManager,
                hr
            )
        else
            editEmployee(
                name,
                id,
                age,
                address,
                phone,
                email,
                password,
                desgination,
                rManager,
                hr
            )

    }

    private fun addEmployee(
        name: String,
        id: String,
        age: String,
        address: String,
        phone: String,
        email: String,
        password: String,
        desgination: String,
        rManager: String,
        hr: String
    ) {
        viewModelScope.launch {
            val result = repository.insertEmployees(
                listOf(
                    Employee(
                        id.toInt(),
                        name,
                        age,
                        address,
                        phone,
                        email,
                        password,
                        desgination,
                        rManager,
                        hr
                    )
                )
            )
            if (result) {
                Toast.makeText(getApplication(), R.string.success, Toast.LENGTH_SHORT).show()
                _onBackPressMLD.value = true
            } else
                Toast.makeText(getApplication(), R.string.something_wrong, Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun editEmployee(
        name: String,
        id: String,
        age: String,
        address: String,
        phone: String,
        email: String,
        password: String,
        desgination: String,
        rManager: String,
        hr: String
    ) {
        val emp = Employee(
            id.toInt(),
            name,
            age,
            address,
            phone,
            email,
            password,
            desgination,
            rManager,
            hr
        )
        emp.uid = uid
        viewModelScope.launch {
            val result = repository.updateEmployee(emp)
            if (result) {
                Toast.makeText(getApplication(), R.string.success, Toast.LENGTH_SHORT).show()
                _onBackPressMLD.value = true
            } else {
                Toast.makeText(getApplication(), R.string.something_wrong, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }
}