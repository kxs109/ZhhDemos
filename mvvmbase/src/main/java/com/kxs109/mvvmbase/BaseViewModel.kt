package com.kxs109.mvvmbase

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    open class UiState<T>(
        val isLoading:Boolean=false,
        val isSuccess:T?=null,
        val isError:String?=null,
        val isRefresh:Boolean=false
    )

    open class BaseUiModel<T>(
        var showLoading:Boolean=false,
        var showSuccess:T?=null,
        var showError:String?=null,
        var showRefresh:Boolean=false
    )

    val exception:MutableLiveData<Throwable> = MutableLiveData()

    fun launchOnMain(block:CoroutineScope.() -> Unit){
        viewModelScope.launch {
            block
        }
    }

    suspend fun <T>launchOnIO(block: CoroutineScope.() -> T){
        withContext(Dispatchers.IO){
            block
        }
    }
}