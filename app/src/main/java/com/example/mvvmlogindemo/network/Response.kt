package com.example.mvvmlogindemo.network

sealed class Response<T>(private var data: T? = null , private var errorMessage: String? = null) {
    class Loading<T>: Response<T>()
    class Sucess<T>(data: T? = null) : Response<T>(data=data)
    class Error<T>(errorMessage: String): Response<T>(errorMessage = errorMessage)
}