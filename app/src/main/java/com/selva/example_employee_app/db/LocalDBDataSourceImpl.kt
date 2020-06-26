package com.selva.example_employee_app.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.model.Employee
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDBDataSourceImpl @Inject constructor(
    private val dao: EmployeeDao,
    private val AttendanceDao: AttendanceDao
) : LocalDBDataSource {
    override fun insertEmployees(employees: List<Employee?>?): List<Long>? {
        val values = arrayListOf<Employee>()
        employees?.forEach { it?.let { values.add(it) } }
        if (values.size > 0)
            return dao.insert(*values.toTypedArray())
        return null
    }

    override fun insertAttendance(employees: List<Attendance?>?): List<Long>? {
        val values = arrayListOf<Attendance>()
        employees?.forEach { it?.let { values.add(it) } }
        if (values.size > 0)
            return AttendanceDao.insert(*values.toTypedArray())
        return null
    }


    override fun getAllEmployees() = dao.getAllEmployees()


    override fun employeesSortByName(name: String?): DataSource.Factory<Int, Employee> {
        if (name == null)
            return dao.getAllEmployees()
        return dao.employeesSortByName(name)
    }


    override fun getEmployee(uid: Int) = dao.getEmployee(uid)
    override fun getAttendance(uid: Int): DataSource.Factory<Int, Attendance> {
        return AttendanceDao.getAttendance(uid);
    }

    override fun getEmployeeDetails(uid: String) = dao.getEmployeeDetails(uid)

    override fun updateEmployee(employee: Employee?): Boolean {
        employee?.let { return dao.update(it) > 0 }
        return false
    }


    override fun deleteEmployee(employee: Employee?): Boolean {
        employee?.let { return dao.delete(it) > 0 }
        return false
    }
}