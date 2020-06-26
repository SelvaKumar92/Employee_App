package com.selva.example_employee_app.viewmodel;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.selva.example_employee_app.model.Employee;
import com.selva.example_employee_app.repository.EmployeeRepository;

import javax.inject.Inject;


public class LoginViewModel extends ViewModel {

    private String mEmail, mPassword;
    private boolean mLoginEnabled;
    private ViewListener mListener;
    private EmployeeRepository mUserRepository;

    @Inject
    public LoginViewModel(EmployeeRepository userRepository) {
        mEmail = "";
        mPassword = "";
        this.mUserRepository = userRepository;
    }

    public void setViewListener(ViewListener listener) {
        this.mListener = listener;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public void setLoginEnabled(boolean loginEnabled) {
        this.mLoginEnabled = loginEnabled;
    }

    public void onLoginClick() {
        try {
            if (mEmail.equals("") || mPassword.equals("")) {
                mListener.onMessage("Please enter valid credentails");
            } else if (mEmail.equals("admin") && mPassword.equals("admin")) {
                mListener.onLoginSuccess(1, -1);
            } else {
                new LoginAsyncTask(mListener, mUserRepository, mEmail, mPassword).execute();
            }
        } catch (Exception e) {
            Log.d("LoginViewModel", "Error while saving: " + e.getMessage());
        } finally {
            setLoginEnabled(true);
        }

    }

    private static class LoginAsyncTask extends AsyncTask<String, Void, Void> {
        ViewListener mListener;
        EmployeeRepository mUserRepository;
        String mEmail, mPassword;
        Employee aEmployee;

        LoginAsyncTask(ViewListener listener, EmployeeRepository mRepository, String mail, String password) {
            mEmail = mail;
            mPassword = password;
            this.mUserRepository = mRepository;
            mListener = listener;
        }


        @Override
        protected Void doInBackground(String... strings) {
            aEmployee = mUserRepository.getEmployeeDetails(mEmail);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (aEmployee != null && aEmployee.getEmail().equals(mEmail)) {
                // User exists in local DB, check for password
                if (aEmployee.getPassword().equals(mPassword)) {
                    // Login successful
                    mListener.onLoginSuccess(2, aEmployee.getUid());
                } else {
                    // Wrong mPassword
                    mListener.onMessage("Wrong password. Please retry.");
                }
            } else {
                // User not found
                mListener.onMessage("Email not Registered. Please Register first.");
            }

        }
    }

    public interface ViewListener {

        void onLoginSuccess(int status, int id);

        void onMessage(String message);
    }
}
