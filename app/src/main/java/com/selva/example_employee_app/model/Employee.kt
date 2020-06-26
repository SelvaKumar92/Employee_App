package com.selva.example_employee_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "employee")
data class Employee(
    @SerializedName("id") @ColumnInfo(name = "id") var id: Int,
    @SerializedName("employee_name") @ColumnInfo(name = "employee_name") var name: String,
    @SerializedName("employee_address") @ColumnInfo(name = "employee_address") var adress: String,
    @SerializedName("employee_age") @ColumnInfo(name = "employee_age") var age: String,
    @SerializedName("employee_phone") @ColumnInfo(name = "employee_phone") var phone: String,
    @SerializedName("employee_email") @ColumnInfo(name = "employee_email") var email: String,
    @SerializedName("employee_password") @ColumnInfo(name = "employee_password") var password: String,
    @SerializedName("employee_des") @ColumnInfo(name = "employee_des") var des: String,
    @SerializedName("employee_rm") @ColumnInfo(name = "employee_rm") var rm: String,
    @SerializedName("employee_hr") @ColumnInfo(name = "employee_hr") var hr: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}