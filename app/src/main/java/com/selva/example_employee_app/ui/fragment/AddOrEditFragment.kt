package com.selva.example_employee_app.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.selva.example_employee_app.R
import com.selva.example_employee_app.databinding.FragmentAddOrEditBinding
import com.selva.example_employee_app.viewmodel.AddOrEditViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_add_or_edit.*
import javax.inject.Inject

class AddOrEditFragment : DaggerFragment() {

    private val args: AddOrEditFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: AddOrEditViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddOrEditBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_or_edit, container, false
        )

        handleLiveData()

        val uid = args.uid

        //edit employee
        if (uid >= 0) {
            viewModel.uid = uid
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.edit_page)
            viewModel.getEmployee(viewModel.uid).observe(this, Observer {
                binding.obj = it
            })
        }
        //add new employee
        else
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.add_page)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_add_edit_activity, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_done) {
            val name = et_name.text.toString()
            val id = et_id.text.toString()
            val age = et_age.text.toString()
            val address = et_address.text.toString()
            val phone = et_phone.text.toString()
            val email = et_email.text.toString()
            val password = et_passepord.text.toString()
            val desgination = et_des.text.toString()
            val rm = et_rm.text.toString()
            val hr = et_hr.text.toString()
            viewModel.doneButtonPressed(
                name,
                id,
                age,
                address,
                phone,
                email,
                password,
                desgination,
                rm,
                hr
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun handleLiveData() {
        viewModel.onBackPressLD.observe(this, Observer {
            if (it)
                activity?.onBackPressed()
        })
    }
}