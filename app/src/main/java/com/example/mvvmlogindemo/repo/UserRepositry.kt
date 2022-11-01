package com.example.mvvmlogindemo.repo

import com.example.mvvmlogindemo.network.ApiService

class UserRepositry(private val apiService: ApiService) {
    suspend fun login(){
        apiService.loginUser()
    }
    suspend fun signUp(){
        apiService.registerUser()
    }
}