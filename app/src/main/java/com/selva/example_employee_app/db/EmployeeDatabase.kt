package com.selva.example_employee_app.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.selva.example_employee_app.model.Attendance
import com.selva.example_employee_app.model.Employee

@Database(entities = [Employee::class, Attendance::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao

    abstract fun AttendanceDao(): AttendanceDao

    companion object {
        const val DATABASE_NAME = "employee_database"
    }
}