package com.example.mvvmlogindemo.viewModel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmlogindemo.modal.loginResponse.LoginResponse
import com.example.mvvmlogindemo.repo.UserRepositry
import kotlinx.coroutines.launch


class LoginViewModel(private val repositry: UserRepositry): ViewModel() {
    var name = MutableLiveData<String>("")
    var password = MutableLiveData<String>("")
    var errMessage = MutableLiveData<String>("")
    val loginResponse: LiveData<LoginResponse>
    get() = repositry.userResponseLiveData// It means when we use/get loginResponse we use is as repositry.userResponseLiveData

    init {

    }

    fun onClick(view: View?) {
        if(name.value?.length!! > 0  ){
            if(password?.value?.length!!>0){
                viewModelScope.launch {
                    repositry.login()
                    errMessage.value = ""
                }
            }
            else{
                errMessage.value = "Please enter the Password"
            }
        }else{
            errMessage.value = "Please enter the Name"
        }

    }
}