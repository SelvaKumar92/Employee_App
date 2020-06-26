package com.selva.example_employee_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.selva.example_employee_app.R
import com.selva.example_employee_app.adapter.AttendanceListAdapter
import com.selva.example_employee_app.databinding.FragmentDetailBinding
import com.selva.example_employee_app.viewmodel.AttendanceViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AttendanceFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: AttendanceViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: AttendanceListAdapter

    private var uid = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        uid = args.uid
        if (uid >= 0) {
            initView(root)
            handleLiveData()
        }
        return root
    }

    private fun initView(root: View) {
        adapter = AttendanceListAdapter(viewModel, R.layout.atttendance_list_item_layout)

        val recyclerView = root.findViewById(R.id.rv) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun handleLiveData() {
        viewModel.getAttendance(uid).observe(this, Observer(adapter::submitList))
    }


    override fun onPause() {
        super.onPause()
    }
}