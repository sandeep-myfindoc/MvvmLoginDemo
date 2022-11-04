package com.example.mvvmlogindemo.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmlogindemo.modal.loginResponse.User
import com.example.mvvmlogindemo.repo.UserRepositry

class SignupViewModel (private val repositry: UserRepositry): ViewModel() {
    var name = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var userData = MutableLiveData<User>()
    fun onClick(view: View?) {
        if(name!=null && name.value?.length!! > 0  ){
            if(password!=null && password?.value?.length!!>0){

            }
            else{

            }
        }else{

        }

    }
}