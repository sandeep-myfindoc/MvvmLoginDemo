package com.example.mvvmlogindemo.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmlogindemo.modal.loginResponse.LoginResponse
import com.example.mvvmlogindemo.network.ApiService
import org.json.JSONObject
import retrofit2.Response

class UserRepositry(private val apiService: ApiService) {
    private val _loginResponseLiveData = MutableLiveData<LoginResponse>()
    val userResponseLiveData: LiveData<LoginResponse>
    get() = _loginResponseLiveData // It means when i get value from userResponseLiveData I get it from _loginResponseLiveData
    suspend fun login(){
        apiService.loginUser()
        //_userResponseLiveData.postValue()
    }
    /*private fun handleResponse(response: Response<LoginResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _loginResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _loginResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _loginResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }*/
    suspend fun signUp(){
        apiService.registerUser()
    }
}