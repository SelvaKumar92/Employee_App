package com.selva.example_employee_app.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.model.Employee


interface EmployeeRepository {

    fun isDummyDataLoaded(): Boolean

    fun setDummyDataLoaded()

    fun getEmployee(uid: Int): LiveData<Employee>

    fun getEmployeeDetails(uid: String): Employee

    fun getAllEmployees(): LiveData<PagedList<Employee>>

    fun getSearchedEmployeeList(name: String): LiveData<PagedList<Employee>>

    suspend fun insertEmployees(employees: List<Employee>): Boolean

    suspend fun insertAttendance(employees: List<Attendance>): Boolean

    fun getAttendance(name: Int): LiveData<PagedList<Attendance>>

    suspend fun updateEmployee(employee: Employee): Boolean

    suspend fun deleteEmployee(employee: Employee): Boolean

    suspend fun getDummyDataFromServerAndLoadToLocalDB(): Boolean
}