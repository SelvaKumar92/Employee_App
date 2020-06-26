package com.selva.example_employee_app.db

import androidx.room.*
import com.selva.example_employee_app.model.Employee
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.selva.example_employee_app.model.Attendance


@Dao
interface AttendanceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg employees: Attendance): List<Long>

    @Query("SELECT * FROM attendance WHERE id = :uid")
    fun getAttendance(uid: Int): DataSource.Factory<Int, Attendance>

}