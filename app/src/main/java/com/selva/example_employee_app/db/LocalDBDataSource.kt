package com.selva.example_employee_app.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.model.Employee

interface LocalDBDataSource {
    fun insertEmployees(employees : List<Employee?>?) : List<Long>?
    fun insertAttendance(employees : List<Attendance?>?) : List<Long>?

    fun getAllEmployees() : DataSource.Factory<Int, Employee>
    fun employeesSortByName(name:String?) : DataSource.Factory<Int,Employee>
    fun getEmployee(uid:Int): LiveData<Employee>
    fun getAttendance(uid:Int): DataSource.Factory<Int,Attendance>

    fun getEmployeeDetails(uid:String): Employee
    fun updateEmployee(employee: Employee?) : Boolean
    fun deleteEmployee(employee: Employee?) : Boolean
}