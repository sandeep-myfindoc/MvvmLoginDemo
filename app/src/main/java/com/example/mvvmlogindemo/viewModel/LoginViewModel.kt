package com.example.mvvmlogindemo.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlogindemo.modal.LoginUser
import com.example.mvvmlogindemo.repo.UserRepositry


class LoginViewModel(private val repositry: UserRepositry): ViewModel() {
    var name = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var message = MutableLiveData<String>()
    private var userMutableLiveData: MutableLiveData<LoginUser> = MutableLiveData<LoginUser>()
    init {
        if (userMutableLiveData == null) {
            userMutableLiveData = MutableLiveData()
        }
    }

    fun getUser():MutableLiveData<LoginUser>{
        return userMutableLiveData
    }

    fun onClick(view: View?) {
        if(name!=null && name.value?.length!! > 0  ){
            if(password!=null && password?.value?.length!!>0){
                val loginUser = LoginUser(name.value!!, password.value!!)
                userMutableLiveData!!.setValue(loginUser)
            }
            else{
                message.value = "Please enter the Password"
            }
        }else{
            message.value = "Please enter the Name"
        }

    }
}