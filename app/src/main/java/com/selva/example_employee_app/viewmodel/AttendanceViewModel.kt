package com.selva.example_employee_app.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.repository.EmployeeRepository
import javax.inject.Inject

class AttendanceViewModel @Inject constructor(application: Application, var repository: EmployeeRepository) : BaseViewModel(application) {


    private val _navigateToDetailActivityMLD = MutableLiveData<Int>()

    override fun onItemClick(id: Int) {
        _navigateToDetailActivityMLD.value = id
    }

    fun getAttendance(id: Int): LiveData<PagedList<Attendance>> {
        return repository.getAttendance(id)
    }

}