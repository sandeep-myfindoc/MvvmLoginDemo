package com.example.mvvmlogindemo.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.repo.UserRepositry
import com.example.mvvmlogindemo.viewModel.LoginViewModel
import com.example.mvvmlogindemo.viewModel.SignupViewModel

class SignUpViewModelFactory(private val userRepositry: UserRepositry): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SignupViewModel(userRepositry) as T
    }
}