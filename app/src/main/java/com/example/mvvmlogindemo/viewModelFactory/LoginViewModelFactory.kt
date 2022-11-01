package com.example.mvvmlogindemo.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmlogindemo.repo.UserRepositry
import com.example.mvvmlogindemo.viewModel.LoginViewModel

class LoginViewModelFactory(private val userRepositry: UserRepositry): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(userRepositry) as T
    }
}