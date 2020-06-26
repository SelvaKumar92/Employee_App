package com.selva.example_employee_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "attendance")
data class Attendance(
    @SerializedName("id") @ColumnInfo(name = "id") var id: Int,
    @SerializedName("time") @ColumnInfo(name = "time") var time: String,
    @SerializedName("is_entry") @ColumnInfo(name = "is_entry") var is_entry: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}