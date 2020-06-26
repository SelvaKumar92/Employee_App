package com.selva.example_employee_app.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.selva.example_employee_app.viewmodel.BaseViewModel
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.selva.example_employee_app.model.Attendance


class AttendanceListAdapter(private val viewModel: BaseViewModel, private val item_layout_id: Int) :
    PagedListAdapter<Attendance, AttendanceListAdapter.AttendanceViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate(
            layoutInflater,
            item_layout_id,
            parent,
            false
        ) as ViewDataBinding
        return AttendanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val employee = getItem(position)
        holder.bind(employee, viewModel)
    }

    fun getEmployeeAt(position: Int): Attendance? = getItem(position)

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Attendance>() {
            override fun areItemsTheSame(
                oldEmployee: Attendance,
                newEmployee: Attendance
            ) = oldEmployee.uid == newEmployee.uid

            override fun areContentsTheSame(
                oldEmployee: Attendance,
                newEmployee: Attendance
            ) = oldEmployee == newEmployee
        }
    }


    inner class AttendanceViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any?, viewModel: BaseViewModel) {
            obj.let {
                binding.setVariable(BR.obj, obj)
            }
            binding.setVariable(BR.viewModel, viewModel)
            binding.executePendingBindings()
        }
    }

}