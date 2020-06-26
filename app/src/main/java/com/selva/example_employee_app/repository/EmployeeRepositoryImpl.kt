package com.selva.example_employee_app.repository

import androidx.paging.Config
import androidx.paging.toLiveData
import com.selva.example_employee_app.model.Employee
import com.selva.example_employee_app.db.LocalDBDataSource
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.preference.SharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeeRepositoryImpl @Inject constructor(
    private val dbDataSource: LocalDBDataSource,
    private val preference: SharedPreference
) : EmployeeRepository {

    override fun isDummyDataLoaded() = preference.isInitDataLoaded()

    override fun setDummyDataLoaded() = preference.initDataLoaded(true)

    override fun getEmployee(uid: Int) = dbDataSource.getEmployee(uid)

    override fun getEmployeeDetails(email: String) = dbDataSource.getEmployeeDetails(email)

    override fun getAllEmployees() = dbDataSource.getAllEmployees()
        .toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 1000))

    override fun getSearchedEmployeeList(name: String) = dbDataSource.employeesSortByName(name)
        .toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 1000))


    override suspend fun insertEmployees(employees: List<Employee>): Boolean {
        var list: List<Long>? = null
        withContext(Dispatchers.IO) {
            list = dbDataSource.insertEmployees(employees)
        }
        if (list == null)
            return false
        else if (list!!.isEmpty())
            return false
        return true
    }

    public override suspend fun insertAttendance(Attendances: List<Attendance>): Boolean {
        var list: List<Long>? = null
        withContext(Dispatchers.IO) {
            list = dbDataSource.insertAttendance(Attendances)
        }
        if (list == null)
            return false
        else if (list!!.isEmpty())
            return false
        return true
    }


    override fun getAttendance(uid: Int) = dbDataSource.getAttendance(uid)
        .toLiveData(Config(pageSize = 30, enablePlaceholders = true, maxSize = 1000))


    override suspend fun updateEmployee(employee: Employee) = withContext(Dispatchers.IO) {
        return@withContext dbDataSource.updateEmployee(employee)
    }

    override suspend fun deleteEmployee(employee: Employee) = withContext(Dispatchers.IO) {
        return@withContext dbDataSource.deleteEmployee(employee)
    }

    override suspend fun getDummyDataFromServerAndLoadToLocalDB(): Boolean {
        return false
    }


}