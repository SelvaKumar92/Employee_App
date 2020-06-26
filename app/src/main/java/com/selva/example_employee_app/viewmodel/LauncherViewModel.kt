package com.selva.example_employee_app.viewmodel

import androidx.lifecycle.*
import com.selva.example_employee_app.repository.EmployeeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LauncherViewModel @Inject constructor(private val repository: EmployeeRepository) : ViewModel(), LifecycleObserver {
    
    private val _navigateToTabActivityMLD = MutableLiveData<Boolean>()

    val navigateToTabActivityLD : LiveData<Boolean> = _navigateToTabActivityMLD

    private lateinit var lifecycle: Lifecycle


    fun addLifeCycleObserver(lifecycle: Lifecycle){
        this.lifecycle = lifecycle
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun removeLifeCycleObserver(){
        lifecycle.removeObserver(this)
    }

    //check if data already loaded from server or not( from pref), if it is loaded navigate to next page
    //if it is not loaded, load and save, then set pref value, then navigate to next page
    //if it is not loaded and failed to load/save data, navigate to next page
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun checkExistingData(){
        if (!repository.isDummyDataLoaded()){
            viewModelScope.launch {
                val result = repository.getDummyDataFromServerAndLoadToLocalDB()
                if (result) {
                    repository.setDummyDataLoaded()
                }else
                _navigateToTabActivityMLD.value = true
            }
        }else {
            _navigateToTabActivityMLD.value = true
        }
    }
}