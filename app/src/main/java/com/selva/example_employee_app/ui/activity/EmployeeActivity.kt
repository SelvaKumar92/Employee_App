package com.selva.example_employee_app.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.selva.example_employee_app.R
import com.selva.example_employee_app.databinding.ActivityEmployeeBinding
import com.selva.example_employee_app.viewmodel.EmployeeViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class EmployeeActivity : DaggerAppCompatActivity() {
    @JvmField
    @Inject
    var viewModel: EmployeeViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityEmployeeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee)
        binding.viewModel = viewModel
        viewModel!!.uid = intent.extras!!.getInt("id")
        val aButton = findViewById<Button>(R.id.btn_logout)
        aButton.setOnClickListener {
            startActivity(Intent(this!!, LoginActivity::class.java))
            finish();
        }
    }
}