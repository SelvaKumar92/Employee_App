package com.selva.example_employee_app.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.selva.example_employee_app.R
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.model.Employee
import com.selva.example_employee_app.repository.EmployeeRepository
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Created by shahbazahmed on 15/08/17.
 */
class EmployeeViewModel @Inject constructor(
    application: Application,
    private var mUserRepository: EmployeeRepository
) :
    AndroidViewModel(application) {
    var uid = -1
    fun onClick(text: String) {
        val df: DateFormat = SimpleDateFormat("EEE, d MMM yyyy, HH:mm")
        val date = df.format(Calendar.getInstance().time)
        insertAttendance(date, text)
    }

    fun entryClick() {
        onClick("Entry")
    }

    fun exitClick() {
        onClick("Exit")
    }


    private fun insertAttendance(date: String, text: String) {
        viewModelScope.launch {
            val result = mUserRepository.insertAttendance(
                listOf(
                    Attendance(
                        uid, date,
                        text
                    )
                )
            )
            if (result) {
                Toast.makeText(getApplication(), R.string.success, Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(getApplication(), R.string.something_wrong, Toast.LENGTH_SHORT)
                    .show()
        }
    }


}