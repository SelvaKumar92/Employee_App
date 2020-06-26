package com.selva.example_employee_app.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rengwuxian.materialedittext.MaterialEditText
import com.selva.example_employee_app.R
import com.selva.example_employee_app.databinding.ActivityLoginBinding
import com.selva.example_employee_app.ui.activity.TabPageActivity
import com.selva.example_employee_app.viewmodel.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity(), LoginViewModel.ViewListener {
    @JvmField
    @Inject
    var viewModel: LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityLoginBinding =
            DataBindingUtil.setContentView(
                this, R.layout.activity_login
            )
        binding.viewModel = viewModel
        viewModel!!.setViewListener(this)
        val emailEditText = findViewById<MaterialEditText>(R.id.et_email_login)
        val passwordEditText =
            findViewById<MaterialEditText>(R.id.et_password_login)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            viewModel!!.email = emailEditText.text.toString()
            viewModel!!.password = passwordEditText.text.toString()
            viewModel!!.onLoginClick()
        }
    }

    override fun onLoginSuccess(status: Int, id: Int) {
        val i: Intent
        when (status) {
            1 -> {
                i = Intent(this@LoginActivity, TabPageActivity::class.java)
                i.putExtra("id", id)
                startActivity(i)
            }
            2 -> {
                i = Intent(this@LoginActivity, EmployeeActivity::class.java)
                i.putExtra("id", id)
                startActivity(i)
            }
        }
    }

    override fun onMessage(message: String) {
        // Hide soft keyboard
        val imm = this.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}